package com.bankingapp.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankingapp.model.Role;
import com.bankingapp.model.User;
import com.bankingapp.repository.UserRepo;

@Service
public class CustomUserDetails implements UserDetailsService {

	@Autowired
	private UserRepo userRepository;

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			role.getPrivileges().stream().map(p -> new SimpleGrantedAuthority(p.getPrivilegeName()))
					.forEach(authorities::add);
		}

		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		Collection<? extends GrantedAuthority> authorities = getAuthorities(user.getRoles());


		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPwd(), authorities);
	}

}
