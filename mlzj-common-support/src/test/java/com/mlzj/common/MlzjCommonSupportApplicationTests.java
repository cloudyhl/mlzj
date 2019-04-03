package com.mlzj.common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjCommonSupportApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void excelTest() throws IOException {
        File file = new File("F://possql.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook sheets = WorkbookFactory.create(fileInputStream);
        Sheet sheetAt = sheets.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        for (short i = 0; i< lastRowNum; i++){
            Row row = sheetAt.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (short j = 0; j < lastCellNum ; j++){
                System.out.println( row.getCell(j).getStringCellValue());
            }

        }
    }

    @Test
    public void excelOut() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet1 = xssfWorkbook.createSheet("sheet1");
        XSSFRow row = sheet1.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("门店");
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("门店编码");
        for (int i = 1; i<5 ;i++){
            XSSFRow row1 = sheet1.createRow(i);
            row1.createCell(0).setCellValue("门店"+i);
            row1.createCell(1).setCellValue("W00"+i);
        }
        FileOutputStream fileOutputStream = new FileOutputStream("F://mlzj.xlsx");
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}

