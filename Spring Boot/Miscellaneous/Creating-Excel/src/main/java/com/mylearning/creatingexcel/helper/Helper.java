package com.mylearning.creatingexcel.helper;

import com.mylearning.creatingexcel.model.StudentTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


public class Helper {

    public static final String[] HEADERS = {
            "ID", "Name", "Age", "Gender"
    };

    public static final String SHEET_NAME = "Student_Details";

    public static Logger logger = LoggerFactory.getLogger(Helper.class);

    public static ByteArrayInputStream dataToExcel(List<StudentTable> list) {

        // try with resources
        try (
                // create workbook ⇒ creating Excel sheet
                Workbook workbook = new XSSFWorkbook();  // Auto-closeable resource
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()  // Auto-closeable resource
        ) {

            Sheet sheet = workbook.createSheet(SHEET_NAME);

            // create row for header, we will use index 0 as for header
            Row row = sheet.createRow(0);

            // creating column cells
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            // creating rows for my values
            int rowIndex = 1;
            for (StudentTable studentTable : list) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(studentTable.getId());
                dataRow.createCell(1).setCellValue(studentTable.getName());
                dataRow.createCell(2).setCellValue(studentTable.getAge());
                dataRow.createCell(3).setCellValue(studentTable.getGender());
            }

            // Finally, write the workbook to the output stream
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (Exception e) {
            logger.error("An error occurred while creating the Excel sheet {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
