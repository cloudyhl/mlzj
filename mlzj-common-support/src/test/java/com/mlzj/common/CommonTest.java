package com.mlzj.common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

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
    public void testGbk() throws UnsupportedEncodingException {
        String s = "<?xml version=\"1.0\" encoding=\"GB2312\"?>\n" +
                "<!-- edited with XMLSPY v5 U (http://www.xmlspy.com) by et8 (et8) -->\n" +
                "<!--Sample XML file generated by XMLSPY v5 U (http://www.xmlspy.com)-->\n" +
                "<caprofile xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"./policy.xsd\">\n" +
                "    <caproperty>\n" +
                "        <name>CA657FENL</name>\n" +
                "        <subjectdn>CN=CA657FENL,C=CN</subjectdn>\n" +
                "        <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "        <provider>KOALLocal</provider>\n" +
                "        <maxcertcount>5000</maxcertcount>\n" +
                "        <token>LOCAL</token>\n" +
                "        <subtoken>1</subtoken>\n" +
                "        <keybit>1024</keybit>\n" +
                "        <keytype>ECC</keytype>\n" +
                "        <type>ca</type>\n" +
                "        <policyType>ECCA</policyType>\n" +
                "    </caproperty>\n" +
                "    <objectpolicy defaultid=\"111\">\n" +
                "        <object desc=\"³¬¼¶¹ÜÀíÔ±Ö¤Êé\" id=\"8\" templatename=\"superAdminCert\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>false</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"5475\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwMC</ext>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\">AwIAgA==</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"¹ÜÀíÔ±Ö¤Êé\" id=\"9\" templatename=\"adminCert\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>false</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"3650\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwMC</ext>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\">AwIAgA==</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"²Ù×÷Ô±Ö¤Êé\" id=\"10\" templatename=\"operCert\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>false</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1825\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwMC</ext>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\">AwIAgA==</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"¸öÈËÆÕÍ¨Ö¤Êé\" id=\"11\" templatename=\"personCert\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>true</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MBQGCCsGAQUFBwMCBggrBgEFBQcDBA==</ext>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\">AwIAgA==</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"Éè±¸Ö¤Êé\" id=\"12\" templatename=\"device\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>false</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <singleEscrowed>false</singleEscrowed>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1825\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwMB</ext>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\">AwIAQA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÓòÃû±¸ÓÃÃû³Æ\"\n" +
                "                        name=\"SubjectAlternativeName\" oid=\"2.5.29.17\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"¶àÓ¦ÓÃ¸ß¼¶Ö¤Êé\" id=\"14\" templatename=\"multi_app_cert\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>true</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <singleEscrowed>false</singleEscrowed>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MBQGCCsGAQUFBwMCBggrBgEFBQcDBA==</ext>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\">AwIAgA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Ê¹ÓÃÕß±¸ÓÃÃû³Æ\"\n" +
                "                        name=\"SubjectAlternativeName\" oid=\"2.5.29.17\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "            <twincertpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MBYGCCsGAQUFBwMEBgorBgEEAYI3FAIC</ext>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAMA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Ê¹ÓÃÕß±¸ÓÃÃû³Æ\"\n" +
                "                        name=\"SubjectAlternativeName\" oid=\"2.5.29.17\"/>\n" +
                "                    <ext critical=\"false\" desc=\"Î¢ÈíÖ¤ÊéÄ£°å\"\n" +
                "                        name=\"MSEnrollType\" oid=\"1.3.6.1.4.1.311.20.2\">HhwAUwBtAGEAcgB0AGMAYQByAGQATABvAGcAbwBu</ext>\n" +
                "                </extensions>\n" +
                "            </twincertpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"Óò¿ØÖÆÆ÷Ö¤Êé\" id=\"15\" templatename=\"MS_CERT_DomainController\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>true</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1825\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIFoA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Î¢ÈíÖ¤ÊéÄ£°å\"\n" +
                "                        name=\"MSEnrollType\" oid=\"1.3.6.1.4.1.311.20.2\">HiAARABvAG0AYQBpAG4AQwBvAG4AdAByAG8AbABsAGUAcg==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MBQGCCsGAQUFBwMCBggrBgEFBQcDAQ==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"SMIMECapabilities\"\n" +
                "                        name=\"SMIMECapabilities\" oid=\"1.2.840.113549.1.9.15\">MDUwCgYIKoZIhvcNAwcwDgYIKoZIhvcNAwICAgCAMA4GCCqGSIb3DQMEAgIAgDAHBgUrDgMCBw==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Ê¹ÓÃÕß±¸ÓÃÃû³Æ\"\n" +
                "                        name=\"SubjectAlternativeName\" oid=\"2.5.29.17\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"Î¢Èí¼ÆËã»úÖ¤Êé\" id=\"16\" templatename=\"MS_CERT_COMPUTER\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>true</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1825\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIFoA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Î¢ÈíÖ¤ÊéÄ£°å\"\n" +
                "                        name=\"MSEnrollType\" oid=\"1.3.6.1.4.1.311.20.2\">Hg4ATQBhAGMAaABpAG4AZQ==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MBQGCCsGAQUFBwMCBggrBgEFBQcDAQ==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Ê¹ÓÃÕß±¸ÓÃÃû³Æ\"\n" +
                "                        name=\"SubjectAlternativeName\" oid=\"2.5.29.17\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"Î¢ÈíÓòÓÃ»§Ö¤Êé\" id=\"17\" templatename=\"MS_CERT_USER\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>true</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIFoA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Î¢ÈíÖ¤ÊéÄ£°å\"\n" +
                "                        name=\"MSEnrollType\" oid=\"1.3.6.1.4.1.311.20.2\">HggAVQBzAGUAcg==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MCAGCisGAQQBgjcKAwQGCCsGAQUFBwMEBggrBgEFBQcDAg==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"SMIMECapabilities\"\n" +
                "                        name=\"SMIMECapabilities\" oid=\"1.2.840.113549.1.9.15\">MDUwCgYIKoZIhvcNAwcwDgYIKoZIhvcNAwICAgCAMA4GCCqGSIb3DQMEAgIAgDAHBgUrDgMCBw==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Ê¹ÓÃÕß±¸ÓÃÃû³Æ\"\n" +
                "                        name=\"SubjectAlternativeName\" oid=\"2.5.29.17\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"Î¢ÈíWEB·þÎñÆ÷Ö¤Êé(DSA)\" id=\"18\" templatename=\"MS_CERT_WEB_DSA\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>false</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIHgA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Î¢ÈíÖ¤ÊéÄ£°å\"\n" +
                "                        name=\"MSEnrollType\" oid=\"1.3.6.1.4.1.311.20.2\">HhIAVwBlAGIAUwBlAHIAdgBlAHI=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwMB</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"Î¢ÈíWEB·þÎñÆ÷Ö¤Êé(RSA)\" id=\"19\" templatename=\"MS_CERT_WEB_RSA\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>false</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIFoA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"Î¢ÈíÖ¤ÊéÄ£°å\"\n" +
                "                        name=\"MSEnrollType\" oid=\"1.3.6.1.4.1.311.20.2\">HhIAVwBlAGIAUwBlAHIAdgBlAHI=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwMB</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"´úÂëÇ©ÃûÖ¤Êé\" id=\"20\" templatename=\"signcode\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>false</publish>\n" +
                "            <keyEncrypt>false</keyEncrypt>\n" +
                "            <singleEscrowed>false</singleEscrowed>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1825\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"true\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwMD</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"General CA\" id=\"21\">\n" +
                "            <type>distCa</type>\n" +
                "            <publish>true</publish>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>subca - Customization</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"5475\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAf8=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"CRL·¢²¼µã\"\n" +
                "                        name=\"CRLDistributionPoints\" oid=\"2.5.29.31\"/>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"General CA\" id=\"22\">\n" +
                "            <type>distCa</type>\n" +
                "            <publish>true</publish>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>subca - Customization</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"5475\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAf8=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"CRL·¢²¼µã\"\n" +
                "                        name=\"CRLDistributionPoints\" oid=\"2.5.29.31\"/>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"General CA\" id=\"23\">\n" +
                "            <type>distCa</type>\n" +
                "            <publish>true</publish>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>subca - Customization</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"5475\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAf8=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"CRL·¢²¼µã\"\n" +
                "                        name=\"CRLDistributionPoints\" oid=\"2.5.29.31\"/>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"General CA\" id=\"24\">\n" +
                "            <type>distCa</type>\n" +
                "            <publish>true</publish>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>subca - Customization</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"5475\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAf8=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"CRL·¢²¼µã\"\n" +
                "                        name=\"CRLDistributionPoints\" oid=\"2.5.29.31\"/>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"General CA\" id=\"25\">\n" +
                "            <type>distCa</type>\n" +
                "            <publish>true</publish>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>subca - Customization</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"5475\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAf8=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"CRL·¢²¼µã\"\n" +
                "                        name=\"CRLDistributionPoints\" oid=\"2.5.29.31\"/>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"¸öÈË¸ß¼¶Ö¤Êé\" id=\"13\" templatename=\"personadvancecert\">\n" +
                "            <type>entity</type>\n" +
                "            <publish>true</publish>\n" +
                "            <keyEncrypt>true</keyEncrypt>\n" +
                "            <keyEncryptAlgo>1.2.156.10197.1.102</keyEncryptAlgo>\n" +
                "            <singleEscrowed>false</singleEscrowed>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAQA=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MBQGCCsGAQUFBwMCBggrBgEFBQcDBA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\">AwIAgA==</ext>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "            <twincertpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "                <signingalg alias=\"ECDSAWithSHA1\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"1095\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAQA=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÔöÇ¿ÐÍÃÜÔ¿ÓÃ·¨\"\n" +
                "                        name=\"ExtKeyUsage\" oid=\"2.5.29.37\">MAoGCCsGAQUFBwME</ext>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAMA==</ext>\n" +
                "                </extensions>\n" +
                "            </twincertpolicy>\n" +
                "        </object>\n" +
                "        <object desc=\"General CA\" id=\"42\">\n" +
                "            <type>distCa</type>\n" +
                "            <publish>true</publish>\n" +
                "            <certpolicy>\n" +
                "                <version>2</version>\n" +
                "                <subjectdnstringtype>subca - Customization</subjectdnstringtype>\n" +
                "                <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "                <validity days=\"5475\" notbefore=\"0\"/>\n" +
                "                <extensions>\n" +
                "                    <ext critical=\"false\" desc=\"ÃÜÔ¿ÓÃ·¨\" name=\"KeyUsage\" oid=\"2.5.29.15\">AwIAwA==</ext>\n" +
                "                    <ext critical=\"false\" desc=\"»ù±¾ÏÞÖÆ\"\n" +
                "                        name=\"BasicConstraints\" oid=\"2.5.29.19\">MAMBAf8=</ext>\n" +
                "                    <ext critical=\"false\" desc=\"CRL·¢²¼µã\"\n" +
                "                        name=\"CRLDistributionPoints\" oid=\"2.5.29.31\"/>\n" +
                "                    <ext critical=\"false\" desc=\"NetscapeCertType\"\n" +
                "                        name=\"NetscapeCertType\" oid=\"2.16.840.1.113730.1.1\"/>\n" +
                "                </extensions>\n" +
                "            </certpolicy>\n" +
                "        </object>\n" +
                "    </objectpolicy>\n" +
                "    <crlpolicies>\n" +
                "        <crlpolicy>\n" +
                "            <version>2</version>\n" +
                "            <subjectdnstringtype>UTF8String</subjectdnstringtype>\n" +
                "            <type>1</type>\n" +
                "            <signingalg alias=\"SM3WithSM2\">1.2.156.10197.1.501</signingalg>\n" +
                "            <entryextensions>\n" +
                "                <ext critical=\"false\" desc=\"·Ï³ýÔ\u00ADÒò\" name=\"crlReason\" oid=\"2.5.29.21\">AwIAmA==</ext>\n" +
                "            </entryextensions>\n" +
                "            <extensions>\n" +
                "                <ext critical=\"false\" desc=\"·Ï³ýÔ\u00ADÒò\"\n" +
                "                    name=\"issuingDistributionPoint\" oid=\"2.5.29.28\">AwIAmA==</ext>\n" +
                "            </extensions>\n" +
                "            <delta>false</delta>\n" +
                "            <indirect>false</indirect>\n" +
                "            <reasonflags>3</reasonflags>\n" +
                "            <validity deltaduration=\"0\" duration=\"4320\" thisupdate=\"0\"/>\n" +
                "            <DistributionPointName>http://192.168.1.100/crl</DistributionPointName>\n" +
                "        </crlpolicy>\n" +
                "    </crlpolicies>\n" +
                "</caprofile>\n";
        System.out.println(new String(s.getBytes("ISO-8859-1"), "GBK"));
    }
}
