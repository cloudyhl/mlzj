package com.mlzj.commontest;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
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

    @Test
    public void testStringLength(){
        String str = "NRCBANK@21+09171232132132@waaddd1+admin@110027000";
        System.out.println(str.length());
        System.out.println("sdsadssdsadsasdsas".length());
        System.out.println("sdsadsadasdsadsadasdasdsa".length());
        System.out.println("sdsasdddddddddddddddddddd".length());
        System.out.println("NRCBANK@+@+@APP_SEQ_NO".length());

    }

    @Test
    public void testString(){
        String user1 = "entCert1";
        String user2 = "entCert4";
        System.out.println(StringUtils.compare(user1, user2));
    }


    @Test
    public void replaceAllHtmlTag() {
        String str = "<p>\t<span style=\"font-size: 14px; color: rgb(51, 51, 51);\">史记》在流传过程中，也窜入了其他文字，失去原貌。今本《史记》中有些篇章或者有些段落不是司马迁所撰写，明显有补窜痕迹，如《司马相如列传》有扬雄以为靡丽之赋劝百而讽一之语，《公孙弘传》中有汉平帝元始中诏赐弘子孙爵语，《贾谊传》中有贾嘉最好学、至孝昭时列为九卿语，等等。而对于《史记》缺篇的补写，裴骃在《太史公自序》末注文中引三国时张晏的话，说《史记》亡十篇，“元、成之间，褚先生补续，作《武帝纪》《三王世家》《龟策》《日者列传》，言辞鄙陋，非迁本意也。”认为褚少孙补了亡佚十篇中的四篇。张守节《龟策列传·正义》则认为褚少孙补十篇，赵翼《廿二史札记》卷一也认为褚少孙补十篇。但是，据《汉书·艺文志》《论衡·须颂篇》《后汉书·班彪传》注及《史通·古今正史篇》等，西汉后期补续《史记》的多达17家。张大可认为真正补续的只有褚少孙一人，其余均为续写西汉史，大都单独别行，与褚少孙续补附骥《史记》而行不同</span>\t<span style=\"color: rgb(51, 102, 204); font-size: 12px;\">&nbsp;[17]</span>\t<span style=\"color: rgb(19, 110, 194);\">&nbsp;</span>\t<span style=\"font-size: 14px; color: rgb(51, 51, 51);\">&nbsp;。赵生群则根据有关资料，认为真正补续《史记》的除褚少孙之外，还有冯商，《汉书·艺文志》对冯商所续《太史公》保留七篇，当是补亡之作；删除四篇，应是续《史记》之文</span></p><p><span style=\"font-size: 14px; color: rgb(51, 51, 51);\"><span class=\"ql-cursor\">\uFEFF</span></span><img src=\"https://testdfs.shuhaisc.com/group2/M00/02/77/rBbjV2MEsHmELzRvAAAAAHDB5Zs286.jpg\"></p>";
        String s = str.replaceAll("<[.[^<]]*>", "");
        System.out.println(s);
    }
}
