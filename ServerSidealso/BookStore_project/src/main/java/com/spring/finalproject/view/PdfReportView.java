package com.spring.finalproject.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.spring.finalproject.model.CartInfo;
import com.spring.finalproject.model.CartLineInfo;
import com.spring.finalproject.model.CustomerInfo;

public class PdfReportView extends AbstractPdfView{
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document,
		PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		
		CartInfo cartInfo = (CartInfo) model.get("CartInfo");
		
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        List<CartLineInfo> cartLineInfo = cartInfo.getCartLines();
        
        //Table table = new Table(4);
        PdfPTable table = new PdfPTable(4);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell("Name");
		table.addCell("Address");
		table.addCell("Email");
		table.addCell("Phone");
		
		//System.out.println("Inside PDF view" + cartInfo.getOrderNum());
		
		table.addCell(customerInfo.getName());
		table.addCell(customerInfo.getAddress());
		table.addCell(customerInfo.getEmail());
		table.addCell(customerInfo.getPhone());
		
		
		//Table table2 = new Table(3);
		PdfPTable table2 = new PdfPTable(3);
		table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table2.addCell("Product Name");
		table2.addCell("Quantity");
		table2.addCell("Amount");
		
		for(CartLineInfo c:cartLineInfo ) {
			table2.addCell(c.getProductInfo().getName());
			table2.addCell(String.valueOf(c.getQuantity()));
			table2.addCell(String.valueOf(c.getAmount()));
			
        }
		
		document.add(new Paragraph("**********ORDER DETAILS**********"));
		document.add(new Paragraph("Order Number: "+cartInfo.getOrderNum()));
		document.add(new Paragraph());
		document.add(new Paragraph("**********CUSTOMER DETAILS**********"));
		document.add(new Paragraph());
		document.add(table);
		document.add(new Paragraph("**********PRODUCT DETAILS IN THE ORDER**********"));
		document.add(new Paragraph());
		document.add(table2);
		
	}

}
