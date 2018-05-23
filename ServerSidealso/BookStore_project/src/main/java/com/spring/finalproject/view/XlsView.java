package com.spring.finalproject.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.apache.poi.ss.usermodel.Workbook;

import com.spring.finalproject.model.OrderInfo;

public class XlsView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"Order-file"+String.valueOf(model.get("page"))+".xls\"");

        @SuppressWarnings("unchecked")
        List<OrderInfo> o = (List<OrderInfo>) model.get("OrderList");
        System.out.println(String.valueOf(o.size()));

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Sheet-AbstractXlsView");

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("OrderNumber");
        header.createCell(1).setCellValue("OrderDate");
        header.createCell(2).setCellValue("CustomerName");
        header.createCell(3).setCellValue("CustomerAddress");
        header.createCell(4).setCellValue("CustomerEmail");
        header.createCell(5).setCellValue("Amount($)");

        //Create data cells
        int rowCount = 1;
        for (OrderInfo or : o){
            Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(or.getOrderNum());
            courseRow.createCell(1).setCellValue(or.getOrderDate());
            courseRow.createCell(2).setCellValue(or.getCustomerName());
            courseRow.createCell(3).setCellValue(or.getCustomerAddress());
            courseRow.createCell(4).setCellValue(or.getCustomerEmail());
            courseRow.createCell(5).setCellValue(or.getAmount());
        }
    }
}
