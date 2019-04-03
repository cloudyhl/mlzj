package com.mlzj.common.controller;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yhl
 * @date 2019/4/2
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/createExcel")
    public void createExcel(HttpServletResponse httpResponse) throws IOException {
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
        httpResponse.setContentType("application/octet-stream");
        httpResponse.addHeader("Content-Disposition", "attachment;filename="
                + new String("梅兰竹菊.xlsx".getBytes(),
                "iso8859-1"));
        ServletOutputStream outputStream = httpResponse.getOutputStream();
        xssfWorkbook.write(outputStream);
    }
}
