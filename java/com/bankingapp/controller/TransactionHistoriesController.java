package com.bankingapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.model.TransactionHistories;
import com.bankingapp.service.TransHistService;
import com.bankingapp.util.GenerateJasperReport;
import com.bankingapp.util.Responses;
import com.bankingapp.util.UserTransactionExcel;
import com.bankingapp.util.UserTransactionsPdf;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping("/api/bank/user")
public class TransactionHistoriesController {

	@Autowired
	private TransHistService transHistService;

	@Autowired
	private UserTransactionExcel excelExporter;

	@Autowired
	private GenerateJasperReport jasperReport;

	@Autowired
	private UserTransactionsPdf pdfExporter;

	@GetMapping("/export")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Resource> exportToExcel(@RequestParam("userName") String userName) throws IOException {
		List<TransactionHistories> transactionHistoriesList = transHistService
				.getTransactionHistoriesByUserName(userName);

		ByteArrayResource byteArrayResource = excelExporter.exportToExcel(transactionHistoriesList);

//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", "attachment; filename=" + userName + "transaction-histories.xlsx");

		return ResponseEntity.ok()
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(byteArrayResource);
	}

	@PostMapping("/filterTransaction")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Object> periodicTransaction(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("from") String from, @RequestParam("to") String to) {

		if (from == null && to == null) {
			return ResponseEntity.badRequest().body(new Responses("Please select the time period."));
		}

		List<TransactionHistories> transactions = transHistService.filterTransactions(accountNumber, from, to);
		return ResponseEntity.ok().body(transactions);
	}

	@PostMapping("/filterTransExcel")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Resource> excelFormat(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("from") String from, @RequestParam("to") String to) throws IOException {

		List<TransactionHistories> transactionHistoriesList = transHistService.filterTransactions(accountNumber, from,
				to);

		ByteArrayResource byteArrayResource = excelExporter.exportToExcel(transactionHistoriesList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=transaction-histories.xlsx");

		return ResponseEntity.ok().headers(headers)
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(byteArrayResource);
	}

	@GetMapping("/exportPdf")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<byte[]> exportToPdf(@RequestParam("userName") String userName) throws IOException {

		try {
			List<TransactionHistories> transactionHistoriesList = transHistService
					.getTransactionHistoriesByUserName(userName);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=transaction-histories.pdf");

			byte[] pdfBytes = pdfExporter.exportUserTransactionInPdf(transactionHistoriesList);
			return ResponseEntity.ok().header("Content-Type", "application/pdf").body(pdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping("/TransactionsDetails/{formatType}")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Object> getTransactionHistoriesPdf(@PathVariable("formatType") String formatType,
			@RequestParam("userName") String userName) {

		if (formatType.equals("excel")) {
			return ResponseEntity.ok()
					.body(new Responses("http://localhost:8080/api/bank/export?userName=" + userName));
		}
		if (formatType.equals("pdf")) {
			return ResponseEntity.ok()
					.body(new Responses("http://localhost:8080/api/bank/exportPdf?userName=" + userName));
		}
		return ResponseEntity.badRequest().body(new Responses("Please select the format-type"));
	}

	@GetMapping("/pdf/history")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Object> generatePdf(HttpServletResponse response) throws DocumentException, IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=transaction-histories.pdf");

		List<TransactionHistories> transactions = transHistService.listAll();

		UserTransactionsPdf trans = new UserTransactionsPdf();

		trans.setTransactions(transactions);

		trans.generate(response);
		return ResponseEntity.ok().header("Content-Type", "application/pdf").build();

	}

	@PostMapping("/userTransactionsReport")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<byte[]> generateTransactionSummaryReport(@RequestParam("userName") String userName) {
        List<TransactionHistories> transactions = transHistService.getTransactionHistoriesByUserName(userName);

        byte[] reportBytes = jasperReport.generateUserTransactionReport(transactions);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=transactionSummaryReport.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(reportBytes);
    }

}
