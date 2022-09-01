package com.admin.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
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
     * @date:
     * @param: time
     * @return: java.lang.Boolean
     **/
    public static Boolean isBeforeNow(String time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, dtf);
        return localDateTime.isBefore(LocalDateTime.now());
    }

    public static Boolean isBeforeToday(String indate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatdate = LocalDate.parse(indate, dtf);
        return formatdate.isBefore(LocalDate.now());
    }

    /**
     * @description: 两个时间对比
     * @author: hy
     * @date:
     * @param: time
     * @return: java.lang.Boolean
     **/
    public static Boolean isBefore(String time1,String time2) {// Java8中日期时间类库，时间比较
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime1 = LocalDateTime.parse(time1, dtf);
        LocalDateTime localDateTime2 = LocalDateTime.parse(time2, dtf);
        return localDateTime1.isBefore(localDateTime2);
    }

    public static Boolean isBeforeTime(String time1,String time2) {// Java8中日期时间类库，时间和日期比较
        time1 = time1.substring(0, 10);//截取时间字符串前10位，获得日期
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateDate1 = LocalDate.parse(time1, dtf);
        LocalDate localDateDate2 = LocalDate.parse(time2, dtf);
        return localDateDate1.isBefore(localDateDate2);
    }

    public static Boolean isBeforeDate(String date1,String date2) {// Java8中日期时间类库，日期比较
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateDate1 = LocalDate.parse(date1, dtf);
        LocalDate localDateDate2 = LocalDate.parse(date2, dtf);
        return localDateDate1.isBefore(localDateDate2);
    }

    public static String getTime(int hour) {// Java8中日期时间类库，加减小时
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime localDateTime = date.minusHours(hour);
        return dateTimeFormatter.format(localDateTime);
    }

    public static String getTimeMin(int min) {// Java8中日期时间类库，加减分钟
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime localDateTime = date.minusMinutes(min);
        return dateTimeFormatter.format(localDateTime);
    }

    public static boolean isNullOrEmpty(String str){
        return str == null || "".equals(str.trim()) || "null".equals(str) || "NULL".equals(str);
    }
    //post方式访问接口
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流

            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String sendGet(String url, String token,String AppKey) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader in;
        url = new String(url.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
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
        // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        in.close();
        return result.toString();
    }

    public static String picModuleType(String str) {
        String ostype = getType();
        String filepath = "";
        switch (str.trim()) {
            case "1"://培训封面
                filepath = "D://source/STMS/trainCover/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/trainCover/";
                }
                return filepath;
            case "2"://题目附件
                filepath = "D://source/STMS/questionPic/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/questionPic/";
                }
                return filepath;
            case "3"://培训视频封面
                filepath = "D://source/STMS/videoCover/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/videoCover/";
                }
                return filepath;
        }
        return filepath;
    }

    public static String sourceModuleType(String str) {
        String ostype = getType();
        String filepath = "";
        switch (str.trim()) {
            case "1"://课程
                filepath = "D://source/STMS/course/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/course/";
                }
                return filepath;
            case "2"://批量导入培训用户信息
                filepath = "D://source/STMS/word/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/word/";
                }
                return filepath;
            case "3"://视频
                filepath = "D://source/STMS/video/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/video/";
                }
                return filepath;
            case "4"://用户
                filepath = "D://source/STMS/user/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/user/";
                }
                return filepath;
            case "5"://培训
                filepath = "D://source/STMS/train/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/train/";
                }
                return filepath;
            case "6"://答题文件资源
                filepath = "D://source/STMS/answer/";
                if (ostype.equals("1")) {
                    filepath = "usr/local/source/STMS/answer/";
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

    public static String saveFileLocal(String basePath, String fileStr, String type) throws Exception {
        File dir = new File(basePath);// 创建文件夹
        // 判断是否存在，不存在创建
        if (!dir.exists()) dir.mkdirs();
        String uuid = (UUID.randomUUID().toString()).replaceAll("-", "");// uuid
        if (type.contains("zip")) {
            type = "zip";
        }
        String filePath = basePath + uuid + "." + type;
        byte[] datafille = decodeBase64(fileStr);// 将String 转成byte数组
        File files = new File(filePath);// 写入本地
        try(FileOutputStream fios = new FileOutputStream(files)){// 创建文件流
            fios.write(datafille, 0, datafille.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return getThirdLocation(filePath);
    }

    public static String saveVideoCover(String fileStr) {//保存培训视频封面
        ByteArrayOutputStream os = VideoUtils.fetchFrame(fileStr);
        FileOutputStream fileOutputStream;
        String basePath = CommonUtils.picModuleType("3");
        File dir = new File(basePath);// 创建文件夹
        // 判断是否存在，不存在创建
        if (!dir.exists()) dir.mkdirs();
        String uuid = (UUID.randomUUID().toString()).replaceAll("-", "");// uuid
        String filePath = basePath + uuid + ".png";
        try {
            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static String savePic(BufferedImage images,String type) throws IOException {//保存题目附件图片
        //将图片以png文件格式保存
        String basePath = CommonUtils.picModuleType(type);
        String uuid = (UUID.randomUUID().toString()).replaceAll("-", "");// uuid
        String picName = uuid + ".png";
        String picPath = basePath+picName;
        File imagefile = new File(picPath);
        ImageIO.write(images, "png", imagefile);
        return picPath;
    }

    /***
     * encode by Base64
     */
    private static byte[] decodeBase64(String input) throws Exception {
        Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, input);
        return (byte[]) retObj;
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
