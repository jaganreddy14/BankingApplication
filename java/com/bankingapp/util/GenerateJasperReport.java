package com.bankingapp.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import com.bankingapp.model.TransactionHistories;

@Service
public class GenerateJasperReport {

	public byte[] generateUserTransactionReport(List<TransactionHistories> transactions) {

		try {
            // Load the JasperReports template (compiled .jasper file)
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\ekambaram_r\\Downloads\\FirstJasper\\TestTemplate..jrxml");

            // Provide a data source (Transaction is a POJO representing your data)
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactions);

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            // Export the report to a byte array (e.g., PDF format)
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
		 } catch (JRException e) {
	            e.printStackTrace();
	            // Handle the exception as needed
	            return new byte[0];
	        }
	}

}
