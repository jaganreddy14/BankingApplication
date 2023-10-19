package com.bankingapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bankingapp.dto.AuthImgDto;
import com.bankingapp.dto.FileResponseDto;
import com.bankingapp.dto.UserActivityDto;
import com.bankingapp.dto.UserDto;
import com.bankingapp.dto.UserFileDto;
import com.bankingapp.enums.UserActivityType;
import com.bankingapp.model.AuthImage;
import com.bankingapp.model.User;
import com.bankingapp.model.UserActivities;
import com.bankingapp.model.UserFiles;
import com.bankingapp.service.AuthImgService;
import com.bankingapp.service.UserActivityService;
import com.bankingapp.service.UserFileService;
import com.bankingapp.service.UserService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserFileService fileService;

	@Autowired
	private AuthImgService authImgService;

	@Autowired
	private UserActivityService activityService;

	@Value("${file.upload.path}")
	private String fileUploadPath;

	@Value("${allowed.extensions}")
	private List<String> allowedExtentions;

	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
		User existingUser = userService.findUserByUserName(userDto.getUserName());

		if (userService.emailAndPasswordValidation(userDto.getEmail(), userDto.getPwd(), userDto.getPhone())) {

			userService.registerUser(userDto);

			UserActivityDto activityDto = new UserActivityDto();
			User findUserId = userService.findUserByUserName(userDto.getUserName());
			activityDto.setUserId(findUserId);
			activityDto.setUserActivity("0");
			activityService.insertUserActivities(activityDto);
			return ResponseEntity.ok().body(new Responses("Registered Successfully..."));
		}
		if (existingUser != null) {
			return ResponseEntity.badRequest().body(new Responses("User already Exists..."));
		}

		return ResponseEntity.badRequest().body(new Responses("Failed email and password validation. "));
	}

	@PostMapping("/getUser")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> getUserInfo() {
		List<User> users = userService.getAllUsersWithRole();
		if (!users.isEmpty()) {
			List<UserDto> usersWithRole = users.stream().map(UserDto::new).collect(Collectors.toList());
			return ResponseEntity.ok(usersWithRole);
		} else {
			return ResponseEntity.badRequest().body(new Responses("User not found."));
		}
	}

	@DeleteMapping("/deleteUser/{id}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
		User foundUser = userService.findUserById(id);
		if (foundUser != null) {
			userService.deleteUserById(id);

			return ResponseEntity.ok().body(new Responses("User deleted."));
		}
		return ResponseEntity.badRequest().body(new Responses("No User Found to delete."));
	}

	@PostMapping("/findUserByName/{userName}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> findByUserName(@PathVariable String userName) {
		User user = userService.findUserByUserName(userName);
		if (user != null) {
			return ResponseEntity.ok().body(user);
		}
		return ResponseEntity.badRequest().body(new Responses("User not found."));
	}

	@PostMapping("/findUserById/{id}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> findById(@PathVariable long id) {
		User user = userService.findUserById(id);
		if (user != null) {
			return ResponseEntity.ok().body(user);
		}
		return ResponseEntity.badRequest().body(new Responses("User not found."));
	}

	@PostMapping("/getActiveUsers")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> findActiveUser() {

		List<User> users = userService.findActiveUser(true);
		if (users != null) {
			return ResponseEntity.ok().body(users);
		}

		return ResponseEntity.badRequest().body(new Responses("No active user found."));
	}

	@PostMapping("/getInactiveUsers")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> findInActiveUser() {

		List<User> users = userService.findActiveUser(false);
		if (users != null) {
			return ResponseEntity.ok().body(users);
		}
		return ResponseEntity.badRequest().body(new Responses("No user found."));
	}

	@PostMapping("/updateUser")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> updateUserInfo(@RequestBody UserDto user) {

		if (userService.updateUserDetails(user)) {
			return ResponseEntity.ok().body(new Responses("User details updated successfully"));
		}
		return ResponseEntity.badRequest().body(new Responses("Something went wrong."));
	}

	@PostMapping("/uploadFile/{userName}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Responses> fileUpload(@RequestParam("file") List<MultipartFile> files,
			@PathVariable("userName") String userName) {

		UserFileDto fileDto = new UserFileDto();

		if (files == null || files.isEmpty()) {
			return ResponseEntity.badRequest().body(new Responses("Please upload file"));
		}

		if (files.size() > 5) {
			return ResponseEntity.badRequest().body(new Responses("Too many files uploaded."));
		}

		for (MultipartFile multipartFile : files) {
			String originalFileName = multipartFile.getOriginalFilename();
			String extentions = userService.getFileExtension(originalFileName);

			if (!userService.isValidExtension(extentions)) {
				return ResponseEntity.badRequest()
						.body(new Responses("Invalid file type or extension: " + originalFileName));
			}

			try {
				Path filePath = Path.of(fileUploadPath, originalFileName);
				Files.write(filePath, multipartFile.getBytes());

				fileDto.setFileName(originalFileName);
				fileDto.setLocation(fileUploadPath + "_" + new Date());
				fileDto.setSize(multipartFile.getSize());
				fileDto.setUserName(userName);

				fileService.fileUploadInfo(fileDto);

			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.badRequest().body(new Responses("File upload failed: " + originalFileName));
			}
		}

		UserActivityDto activityDto = new UserActivityDto();
		User findUserId = userService.findUserByUserName(userName);
		activityDto.setUserId(findUserId);
		activityDto.setUserActivity("7");
		activityService.insertUserActivities(activityDto);
		return ResponseEntity.ok().body(new Responses("File upload successful"));

	}

	@PostMapping("/downloadFiles/{fileName:.+}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Resource> downloadfilesWithFileType(@PathVariable("fileName") String fileName) {

		try {
			Path filePath = Path.of(fileUploadPath, fileName);
			Resource resource = new FileSystemResource(filePath);

			if (resource.exists() && resource.isReadable()) {
				HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

				return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
						.contentType(MediaType.IMAGE_PNG).body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@PostMapping("/fileUploadInfo/{userName}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> retriveUserFileuploadInfo(@PathVariable("userName") String userName) {
		UserFiles foundFile = fileService.getbyUserName(userName);
		if (foundFile == null) {
			return ResponseEntity.badRequest().body(new Responses("No information found."));
		}
		List<FileResponseDto> response = new ArrayList<FileResponseDto>();
		FileResponseDto responseDto = new FileResponseDto();
		responseDto.setFileName(foundFile.getFileName());
		responseDto.setSize(foundFile.getSize());
		responseDto.setUploadedDate(foundFile.getCreatedDate());
		responseDto.setDownloadLink("http://localhost:8080/api/bank/downloadFiles" + "/" + foundFile.getFileName());
		response.add(responseDto);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/uploadsecurityImg")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> uploadUserSecurityImage(@RequestParam("file") MultipartFile file,
			@RequestParam("userName") String userName) {

		if (file == null) {
			return ResponseEntity.badRequest().body(new Responses("Please Select an Image"));
		}

		String fileName = file.getOriginalFilename();
		String extention = authImgService.getFileExtension(fileName);

		try {
			Path filePath = Path.of(fileUploadPath, fileName);
			Files.write(filePath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		AuthImgDto authImgDto = new AuthImgDto();
		authImgDto.setImage(fileName);
		authImgDto.setUserName(userName);
		authImgDto.setLocation(fileUploadPath);

		if (fileName == null && extention == null) {
			return ResponseEntity.badRequest().body(new Responses("something went wrong."));
		} else {
			authImgService.saveImage(authImgDto);
		}

		return ResponseEntity.ok().body(new Responses("Security Image have be set Successfully."));
	}

	@PostMapping("/getSecurityImg")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Resource> downloadUserSecurityImg(@RequestParam("userName") String userName) {

		if (userName == null) {
			return ResponseEntity.badRequest().build();
		}

		AuthImage findImage = authImgService.getImageByUserName(userName);

		try {

			Path filePath = Path.of(findImage.getLocation(), findImage.getImgName());
			Resource resource = new FileSystemResource(filePath);

			if (resource.exists() && resource.isReadable()) {
				HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + findImage.getImgName());

				return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
						.contentType(MediaType.IMAGE_PNG).body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (

		IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	// forgot password;
}
