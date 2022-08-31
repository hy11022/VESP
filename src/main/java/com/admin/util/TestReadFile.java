package com.admin.util;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class TestReadFile {

    public static String writeImg(Map<String, XSSFPictureData> picMap, int row, int col) {
        //将图片以png文件格式保存
        String basePath = CommonUtils.picModuleType("1");
        String uuid = (UUID.randomUUID().toString()).replaceAll("-", "");// uuid
        String picName = uuid + ".png";
        String picPath = basePath+picName;
        PictureData picData = picMap.get(row+":"+col);
        if (picData == null) {
            return "";
        }
        byte[] data = picData.getData();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(picPath);
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("excelpicPathPath:"+picPath);
        return picPath;
    }

    /**
     * 获取图片和位置 (xlsx)
     */
    public static Map<String, XSSFPictureData> getPictures (XSSFSheet sheet) {
        Map<String, XSSFPictureData> map = new HashMap<>();
        List<POIXMLDocumentPart> list = sheet.getRelations();
        for (POIXMLDocumentPart part : list) {
            if (part instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) part;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture picture = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = picture.getPreferredSize();
                    CTMarker marker = anchor.getFrom();
                    String key = marker.getRow() + ":" + marker.getCol();
                    map.put(key, picture.getPictureData());
                }
            }
        }
        return map;
    }
}