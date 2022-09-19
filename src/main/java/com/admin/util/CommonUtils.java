package com.admin.util;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @BelongsProject: VESP
 * @BelongsPackage: com.admin.util
 * @Author: Du Rongjun
 * @CreateTime: 2022-07-07  14:11
 * @Description:
 * @Version: 1.0
 */
public class CommonUtils {
    /**
     * @description: 判断是否在当前时间之前，改
     * @author: Du Rongjun
     * @param: time
     * @return: java.lang.Boolean
     **/
    public static Boolean isBeforeNow(String time) {
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, dtfa);
        return localDateTime.isBefore(LocalDateTime.now());
    }

    public static Boolean isBeforeToday(String indate) {
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatdate = LocalDate.parse(indate, dtfa);
        return formatdate.isBefore(LocalDate.now());
    }

    /**
     * @description: 两个时间对比
     * @author: hy
     * @param: time
     * @return: java.lang.Boolean
     **/
    public static Boolean isBefore(String time1,String time2) {// Java8中日期时间类库，时间比较
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime1 = LocalDateTime.parse(time1, dtfa);
        LocalDateTime localDateTime2 = LocalDateTime.parse(time2, dtfa);
        return localDateTime1.isBefore(localDateTime2);
    }

    public static Boolean isBeforeTime(String time1,String time2) {// Java8中日期时间类库，时间和日期比较
        time1 = time1.substring(0, 10);//截取时间字符串前10位，获得日期
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateDate1 = LocalDate.parse(time1, dtfa);
        LocalDate localDateDate2 = LocalDate.parse(time2, dtfa);
        return localDateDate1.isBefore(localDateDate2);
    }

    public static Boolean isBeforeDate(String date1,String date2) {// Java8中日期时间类库，日期比较
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateDate1 = LocalDate.parse(date1, dtfa);
        LocalDate localDateDate2 = LocalDate.parse(date2, dtfa);
        return localDateDate1.isBefore(localDateDate2);
    }

    public static String getTime(int hour) {// Java8中日期时间类库，加减小时
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime localDateTime = date.minusHours(hour);
        return dtfa.format(localDateTime);
    }

    public static String getTimeMin(int min) {// Java8中日期时间类库，加减分钟
        DateTimeFormatter dtfa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime localDateTime = date.minusMinutes(min);
        return dtfa.format(localDateTime);
    }

    public static boolean isNullOrEmpty(String str){
        return str == null || "".equals(str.trim()) || "null".equals(str) || "NULL".equals(str);
    }

    //post方式访问接口
    public static String sendPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        URLConnection conn =null;
        try{
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            assert conn != null;
            try(PrintWriter out = new PrintWriter(conn.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),StandardCharsets.UTF_8))) {
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String sendGet(String url, String token,String AppKey){
        StringBuilder result = new StringBuilder();
        url = new String(url.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        URLConnection connection =null;
        try{
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            connection = realUrl.openConnection();
            //设置超时时间
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(15000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setRequestProperty("token", token);
            connection.setRequestProperty("AppKey", AppKey);
            // 建立实际的连接
            connection.connect();
        }catch (IOException e){
            e.printStackTrace();
        }
        // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
        try {
            assert connection != null;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))){
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e){
           e.printStackTrace();
        }
        return result.toString();
    }

    public static String sourceModuleType(String str) {
        String ostype = getType();
        String filepath = "";
        switch (str.trim()) {
            case "1"://功能模块
                filepath = "D://source/VESP/function/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/VESP/function/";
                }
                return filepath;
            case "2"://新闻
                filepath = "D://source/VESP/news/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/VESP/news/";
                }
                return filepath;
            case "3"://视频
                filepath = "D://source/VESP/video/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/VESP/video/";
                }
                return filepath;
            case "4"://用户
                filepath = "D://source/VESP/user/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/VESP/user/";
                }
                return filepath;
            case "5"://培训
                filepath = "D://source/VESP/train/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/VESP/train/";
                }
                return filepath;
            case "6"://答题文件资源
                filepath = "D://source/VESP/answer/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/VESP/answer/";
                }
                return filepath;
        }
        return filepath;
    }

    public static String getType() {//操作系统类别
        String ostype = "1";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            ostype = "2";
        }
        return ostype;
    }

    public static String saveFileLocal(String basePath, String fileStr, String type) {
        File dir = new File(basePath);// 创建文件夹
        if (!dir.exists()) {// 判断是否存在，不存在创建
            dir.mkdirs();
        }
        String uuid = (UUID.randomUUID().toString()).replaceAll("-", "");// uuid
        if (type.contains("zip")) {
            type = "zip";
        }
        String filePath = basePath + uuid + "." + type;
        byte[] datafille = new Base64().decode(fileStr);// 将String 转成byte数组
        File files = new File(filePath);// 写入本地
        try (FileOutputStream writefile= new FileOutputStream(files)){
            writefile.write(datafille, 0, datafille.length);
            writefile.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getThirdLocation(filePath);// 获取倒数第三个/之后字符
    }

    public static String getThirdLocation(String url) {// 获取倒数第三个/之后字符
        // index为最后的“/”字符所在的位置
        int index = url.lastIndexOf("/");
        // 从最后的“/”字符的前一个位置向前找“/”的位置为此index
        index = url.lastIndexOf("/", index - 1);
        // 从倒数第二的“/”字符的前一个位置向前找“/”的位置为此index
        index = url.lastIndexOf("/", index - 1);
        // 得到倒数第三个“/”之后的字符串
        return url.substring(index);
    }
}