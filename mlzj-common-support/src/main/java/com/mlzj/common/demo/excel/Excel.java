package com.mlzj.common.demo.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;

/**
 * @author yhl
 * @date 2019/4/12
 */
public class Excel extends DefaultHandler {

    /**
     * SXSSFWorkbook 用于大量数据导出
     * XSSFWorkbook  数据量270000时系统卡死，cpu负载100%
     * @throws IOException
     */
    public static void excelOut() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet1 = xssfWorkbook.createSheet("sheet1");
        XSSFRow row = sheet1.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("门店");
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("门店编码");
        long startTime = System.currentTimeMillis();
        for (int i = 1; i<1000000 ;i++){
            XSSFRow row1 = sheet1.createRow(i);
            row1.createCell(0).setCellValue("门店"+i);
            row1.createCell(1).setCellValue("W00"+i);
            row1.createCell(2).setCellValue("W00"+i);
            row1.createCell(3).setCellValue("W00"+i);
            row1.createCell(4).setCellValue("W00"+i);
            row1.createCell(5).setCellValue("W00"+i);
            row1.createCell(6).setCellValue("W00"+i);
            row1.createCell(7).setCellValue("W00"+i);
            row1.createCell(8).setCellValue("W00"+i);
            row1.createCell(9).setCellValue("W00"+i);
            if (i %1000 == 0){
                System.out.println(i);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("F://mlzj.xlsx");
        xssfWorkbook.write(fileOutputStream);
        System.out.println(System.currentTimeMillis() - startTime);
        fileOutputStream.close();
    }

    /**
     * 该方法读取excel容易导致内存溢出Java heap space OutOfMemory
     * @throws IOException
     */
    public static void excelTest() throws IOException {
        File file = new File("F://safeStock.xlsx");
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

    public static void largeExcelRead() throws IOException, OpenXML4JException {
        OPCPackage opcPackage = OPCPackage.open("F://mlzj.xlsx");
        XSSFReader xssfReader = new XSSFReader(opcPackage);
        SharedStringsTable sst = xssfReader.getSharedStringsTable();
    }

    public static void excelLargeOut() throws IOException {
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        SXSSFSheet sheet1 = xssfWorkbook.createSheet("sheet1");
        SXSSFRow row = sheet1.createRow(0);
        SXSSFCell cell = row.createCell(0);
        cell.setCellValue("商品编号");
        SXSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("门店编码");
        SXSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("安全库存");
        long startTime = System.currentTimeMillis();
        for (int i = 1; i<50 ;i++){
            SXSSFRow row1 = sheet1.createRow(i);
            row1.createCell(0).setCellValue("20000x"+i);
            row1.createCell(1).setCellValue("W00x"+i);
            row1.createCell(2).setCellValue(""+i);
            if (i %1000 == 0){
                System.out.println(i);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("F://safeStock.xlsx");
        xssfWorkbook.write(fileOutputStream);
        System.out.println(System.currentTimeMillis()-startTime);
        fileOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = new FileInputStream("F://mlzj.xlsx");
//        // 解析每行结果在listener中处理
//        ExcelListener listener = new ExcelListener();
//
//        ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
//        excelReader.read();
        excelLargeOut();
    }
}
