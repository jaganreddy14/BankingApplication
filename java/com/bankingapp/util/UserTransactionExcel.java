package com.bankingapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import com.bankingapp.model.TransactionHistories;

@Component
public class UserTransactionExcel {

	@Value("${file.upload.path}")
	private String fileDirectory;

	public ByteArrayResource exportToExcel(List<TransactionHistories> transactionHistoriesList) throws IOException {
		
		String filePath = fileDirectory + File.separator + "Transaction History.xlsx";
		FileInputStream fileInputStream = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheetAt(0);

		int rowNum = 1;

		for (TransactionHistories transactionHistory : transactionHistoriesList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(transactionHistory.getUserName());
			row.createCell(1).setCellValue(transactionHistory.getTransactionId());
			row.createCell(2).setCellValue(transactionHistory.getAccountNumber().longValue());
			row.createCell(3).setCellValue(transactionHistory.getTransactionAmount().doubleValue());
			row.createCell(4).setCellValue(transactionHistory.getTransactionType().toString());
			row.createCell(5).setCellValue(transactionHistory.getTransactionStatus().toString());
			row.createCell(6).setCellValue(transactionHistory.getCreatedDate());
		}
		FileOutputStream outputStream = new FileOutputStream(filePath);
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		workbook.close();
		fileInputStream.close();

		File updatedFile = new File(filePath);
		byte[] byteArray = new byte[(int) updatedFile.length()];
		FileInputStream fis = new FileInputStream(updatedFile);
		fis.read(byteArray);
		fis.close();
		return new ByteArrayResource(byteArray);

	}

}
