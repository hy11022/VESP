package com.admin.util;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import java.util.*;

public class TestReadFile {

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