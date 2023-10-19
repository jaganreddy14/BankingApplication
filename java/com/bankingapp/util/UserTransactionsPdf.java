package com.bankingapp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bankingapp.model.TransactionHistories;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class UserTransactionsPdf {

	List<TransactionHistories> transactions;

	public List<TransactionHistories> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionHistories> transactions) {
		this.transactions = transactions;
	}

	public byte[] exportUserTransactionInPdf(List<TransactionHistories> transactionHistories) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {

			Document document = new Document(PageSize.A4);

			PdfWriter.getInstance(document, outputStream);

			document.open();

			for (TransactionHistories transactionHistories2 : transactionHistories) {
				document.add(new Paragraph("User Transaction Details"));
				document.add(new Paragraph("Transaction ID: " + transactionHistories2.getTransactionId()));
				document.add(new Paragraph("Name: " + transactionHistories2.getUserName()));
				document.add(new Paragraph("Account Number: " + transactionHistories2.getAccountNumber()));
				document.add(new Paragraph("Transaction Amount" + transactionHistories2.getTransactionAmount()));
				document.add(new Paragraph("status: " + transactionHistories2.getTransactionStatus()));
				document.add(new Paragraph("Payment Type: " + transactionHistories2.getTransactionType()));
			}
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}

	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph paragraph = new Paragraph("Transaction Histories", fontTiltle);

		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(6);

		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 2, 2, 2, 2, 2, 2 });
		table.setSpacingBefore(5);

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(CMYKColor.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		cell.setPhrase(new Phrase("User Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Transaction Id", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Account Number", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Transaction Amount", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Transaction Status", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Transaction Date", font));
		table.addCell(cell);

		for (TransactionHistories transactionHistories2 : transactions) {
			table.addCell(transactionHistories2.getUserName());
			table.addCell(transactionHistories2.getTransactionId());
			table.addCell(transactionHistories2.getAccountNumber().toString());
			table.addCell(transactionHistories2.getTransactionAmount().toString());
			table.addCell(transactionHistories2.getTransactionStatus().toString());
			table.addCell(transactionHistories2.getCreatedDate().toString());
		}
		document.add(table);
		document.close();

	}

}
