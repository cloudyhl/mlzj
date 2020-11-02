package com.mlzj.commontest;


import com.sun.deploy.net.HttpUtils;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tests {

    @Test
    public void comFuture() throws InterruptedException {

        Thread th = new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        });
        CompletableFuture.runAsync(th).thenRun(new Thread(()-> System.out.println(2)));
        System.out.println("2312");
        Thread.sleep(1000);

    }

    @Test
    public void fileInput() throws IOException {
        File file = new File("F://docker.pdf");
        FileReader fileReader = new FileReader(file);
        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();
        FileOutputStream fileOutputStream = new FileOutputStream("F://dockerCopy.pdf");
        long s = System.currentTimeMillis();
        int len = 0;
        while ((len = in.read(bytes)) >0){
            fileOutputStream.write(bytes,0,len);
        }
        System.out.println(System.currentTimeMillis() - s);
        in.close();
        fileOutputStream.close();
    }

    @Test
    public void url() throws UnsupportedEncodingException {
        String decode = URLEncoder.encode("https://scm-pim.oss-cn-shanghai.aliyuncs.com/image/genimg/gap_csv_photobase/DIPV1-gf7j18700umn5upgqjqb/主图/唯品会/577855-主图-1-577855GREY HEATHER B03.jpg?etag=1588045966129", "UTF-8");
        System.out.println(decode);
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher("https://scm-pim.oss-cn-shanghai.aliyuncs.com/image/genimg/gap_csv_photobase/DIPV1-gf7j18700umn5upgqjqb/主图/唯品会/577855-主图-1-577855GREY HEATHER B03.jpg?etag=1588045966129");
        StringBuffer stringBuffer = new StringBuffer("");
        while (m.find()){
            m.appendReplacement(stringBuffer, URLEncoder.encode(m.group(), StandardCharsets.UTF_8.name()));
            System.out.println("x");
        }
        m.appendTail(stringBuffer);
        System.out.println(stringBuffer.toString().replace(" ","%20"));

    }

    @Test
    public void parseDate() throws ParseException {
        String date = "2020-06-11";
        Date date1 = DateUtils.parseDate(date, "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void status(){
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.plusDays(-7);
        System.out.println(localDate);
        System.out.println(String.valueOf(localDate1));

    }

    @Test
    public void runtime() throws IOException {
        Process netstat = Runtime.getRuntime().exec("cmd /c netstat");
        InputStream inputStream = netstat.getInputStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        System.out.println(new String(bytes));
    }

    @Test
    public void zero(){
        BigDecimal bigDecimal = new BigDecimal("0");
        System.out.println(bigDecimal);
    }

}
