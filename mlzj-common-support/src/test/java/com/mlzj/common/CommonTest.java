package com.mlzj.common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CommonTest {


    @Test
    public void testExcel() throws IOException, InterruptedException {
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        SXSSFSheet sheet1 = xssfWorkbook.createSheet("sheet1");
        SXSSFRow rows = sheet1.createRow(0);
        SXSSFCell cell = rows.createCell(0);
        cell.setCellValue("文书ID");
        SXSSFCell cell1 = rows.createCell(1);
        cell1.setCellValue("doc");
        SXSSFCell cell2 = rows.createCell(2);
        cell2.setCellValue("听证");
        SXSSFCell cell3 = rows.createCell(3);
        cell3.setCellValue("补救");
        SXSSFCell cell4 = rows.createCell(4);
        cell4.setCellValue("改变行政");
        SXSSFCell cell5 = rows.createCell(5);
        cell5.setCellValue("听证所在分句");
        SXSSFCell cell6 = rows.createCell(6);
        cell6.setCellValue("补救所在分句");
        SXSSFCell cell7 = rows.createCell(7);
        cell7.setCellValue("改变行政所在分句");
        String a = "听证";
        String b = "补救";
        String c = "改变行政";
        File file = new File("F://xxx.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook sheets = WorkbookFactory.create(fileInputStream);
        Sheet sheetAt = sheets.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        for (short i = 1; i< lastRowNum; i++){
            System.out.println(i);
            if (i == 3738){
                Thread.sleep(3);
            }
            Row row = sheetAt.getRow(i);
            short lastCellNum = row.getLastCellNum();
            SXSSFRow row1 = sheet1.createRow(i);
            row1.createCell(0).setCellValue(row.getCell(0).getStringCellValue());
            row1.createCell(1).setCellValue(row.getCell(1).getStringCellValue());
            for (short j = 0; j < lastCellNum ; j++){
                if (j == 1) {
                    if (row.getCell(j).getStringCellValue().indexOf(a) > 0) {

                        row1.createCell(2).setCellValue("是");
                        int start = 0;
                        int end = row.getCell(j).getStringCellValue().length();
                        int startIndex = row.getCell(j).getStringCellValue().indexOf(a);
                        int endIndex = row.getCell(j).getStringCellValue().indexOf(a);
                        while (startIndex>0 && startIndex<row.getCell(j).getStringCellValue().length()){
                            if ('。' == row.getCell(j).getStringCellValue().charAt(startIndex)){
                                start = startIndex;
                                break;
                            }
                            startIndex--;
                        }
                        while (endIndex>0 && endIndex<row.getCell(j).getStringCellValue().length()){
                            if ('。' == row.getCell(j).getStringCellValue().charAt(endIndex)){
                                end = endIndex;
                                break;
                            }
                            endIndex++;
                        }
                        String str = row.getCell(j).getStringCellValue().substring(start,end);
                        row1.createCell(5).setCellValue(str);
                    }else {
                        row1.createCell(2).setCellValue("否");
                    }
                    if (row.getCell(j).getStringCellValue().indexOf(b) > 0) {
                        row1.createCell(3).setCellValue("是");
                        int start = 0;
                        int end = row.getCell(j).getStringCellValue().length();
                        int startIndex = row.getCell(j).getStringCellValue().indexOf(a);
                        int endIndex = row.getCell(j).getStringCellValue().indexOf(a);
                        while (startIndex>0 && startIndex<row.getCell(j).getStringCellValue().length()){
                            if ('。' == row.getCell(j).getStringCellValue().charAt(startIndex)){
                                start = startIndex;
                                break;
                            }
                            startIndex--;
                        }
                        while (endIndex>0 && endIndex<row.getCell(j).getStringCellValue().length()){
                            if ('。' == row.getCell(j).getStringCellValue().charAt(endIndex)){
                                end = endIndex;
                                break;
                            }
                            endIndex++;
                        }
                        String str = row.getCell(j).getStringCellValue().substring(start,end);
                        row1.createCell(6).setCellValue(str);
                    }else {
                        row1.createCell(3).setCellValue("否");
                    }
                    if (row.getCell(j).getStringCellValue().indexOf(c) > 0) {
                        row1.createCell(4).setCellValue("是");
                        int start = 0;
                        int end = row.getCell(j).getStringCellValue().length();
                        int startIndex = row.getCell(j).getStringCellValue().indexOf(a);
                        int endIndex = row.getCell(j).getStringCellValue().indexOf(a);
                        while (startIndex>0 && startIndex<row.getCell(j).getStringCellValue().length()){
                            if ('。' == row.getCell(j).getStringCellValue().charAt(startIndex)){
                                start = startIndex;
                                break;
                            }
                            startIndex--;
                        }
                        while (endIndex>0 && endIndex<row.getCell(j).getStringCellValue().length()){
                            if ('。' == row.getCell(j).getStringCellValue().charAt(endIndex)){
                                end = endIndex;
                                break;
                            }
                            endIndex++;
                        }
                        String str = row.getCell(j).getStringCellValue().substring(start,end);
                        row1.createCell(7).setCellValue(str);
                    } else {
                        row1.createCell(4).setCellValue("否");
                    }
                }
            }

        }
        FileOutputStream fileOutputStream = new FileOutputStream("F://goOut.xlsx");
        xssfWorkbook.write(fileOutputStream);
    }

    @Test
    public void test() throws IOException {
        SXSSFSheet sheet1;
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
        sheet1 = sxssfWorkbook.createSheet("sheet1");
//        for (int index = 0; index < CommonConstants.CERT_HEADERS.size(); index++) {
//            SXSSFCell cell = row.createCell(index);
//            cell.setCellValue(CommonConstants.CERT_HEADERS.get(index));
//        }
        FileOutputStream fileOutputStream = new FileOutputStream("F://goOut.xlsx");
        sxssfWorkbook.write(fileOutputStream);
    }

    @Test
    public void getGeoLine(){
        GlobalCoordinates geo1 = new GlobalCoordinates(34.233636, 108.888399);
        GlobalCoordinates geo2 = new GlobalCoordinates(30.58372, 104.07447);
        double distanceMeter = getDistanceMeter(geo1, geo2, Ellipsoid.Sphere);
        System.out.println((int)distanceMeter/1000);

    }

    public double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){

        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }

}
