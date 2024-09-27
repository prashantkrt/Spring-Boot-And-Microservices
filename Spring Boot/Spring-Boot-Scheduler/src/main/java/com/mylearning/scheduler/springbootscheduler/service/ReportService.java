package com.mylearning.scheduler.springbootscheduler.service;


import com.mylearning.scheduler.springbootscheduler.model.Order;
import com.mylearning.scheduler.springbootscheduler.repository.OrderRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private OrderRepo orderRepo;

    public byte[] generateReport() throws IOException {

        List<Order> orders = orderRepo.findAll();

        // multiple ways :)
        //XSSFWorkbook workbook = new XSSFWorkbook();
        //Workbook workbook = new SXSSFWorkbook();
        //Workbook workbook1 = new XSSFWorkbook();
        //XSSFSheet sheet = new XSSFWorkbook().createSheet();
        //SXSSFWorkbook sheets = new SXSSFWorkbook();

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");
        writeHeaderLine(sheet);

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Order order : orders) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, order.getId(), style);
            createCell(row, columnCount++, order.getName(), style);
            createCell(row, columnCount++, order.getQuantity(), style);
            createCell(row, columnCount++, order.getPrice(), style);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (bos) {
            workbook.write(bos);
        }
        return bos.toByteArray();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeHeaderLine(Sheet sheet) {

        //create HeaderRow
        Row headerRow = sheet.createRow(0);

        //creating cell of header row
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ORDER_ID");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("ORDER_NAME");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("QUANTITY");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("PRICE");

    }

}
