package com.admin.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.interfaces.ITable;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import com.spire.doc.*;

import java.io.*;
import java.util.*;

public class GetTable {
    public static List<String> readQuestionWord(String filePath) {
        //加载Word测试文档
        Document docx = new Document();
        docx.loadFromFile(filePath);
        //获取第一节
        Section section = docx.getSections().get(0);
        //获取第一个表格
        ITable table = section.getTables().get(0);
        //创建List
        List<String> s = new ArrayList<>();
        //遍历表格中的行
        for (int i = 1; i < table.getRows().getCount(); i++) {
            TableRow row = table.getRows().get(i);
            JSONObject textList = new JSONObject();
            //遍历每行中的单元格
            for (int j = 0; j < row.getCells().getCount(); j++) {
                TableCell cell = row.getCells().get(j);
                //遍历单元格中的段落
                String wordText = "";
                for (int k = 0; k < cell.getParagraphs().getCount(); k++) {
                    Paragraph paragraph = cell.getParagraphs().get(k);
                    wordText = paragraph.getText();//获取文本内容
                    //遍历段落中的所有子对象
                    for (int x = 0; x < paragraph.getChildObjects().getCount(); x++) {
                        Object object = paragraph.getChildObjects().get(x);
                        //判定对象是否为图片
                        if (object instanceof DocPicture) {
                            //获取图片
                            DocPicture picture = (DocPicture) object;
                            try {
                                wordText = CommonUtils.savePic(picture.getImage(), "2");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (j == 0) {
                    textList.put("knowledgePoint", wordText);
                } else if (j == 1) {
                    textList.put("level", wordText);
                } else if (j == 2) {
                    textList.put("userType", wordText);
                } else if (j == 3) {
                    textList.put("type", wordText);
                } else if (j == 4) {
                    textList.put("content", wordText);
                } else if (j == 5) {
                    textList.put("image", wordText);
                } else if (j == 6) {
                    textList.put("selectA", wordText);
                } else if (j == 7) {
                    textList.put("selectB", wordText);
                } else if (j == 8) {
                    textList.put("selectC", wordText);
                } else if (j == 9) {
                    textList.put("selectD", wordText);
                } else if (j == 10) {
                    textList.put("answer", wordText);
                }
            }
            s.add(textList.toString());
        }
        return s;
    }

    public static List<String> readCourseWord(String filePath) {
        //加载Word测试文档
        Document docx = new Document();
        docx.loadFromFile(filePath);
        //获取第一节
        Section section = docx.getSections().get(0);
        //获取第一个表格
        ITable table = section.getTables().get(0);
        //创建List
        List<String> s = new ArrayList<>();
        //遍历表格中的行
        for (int i = 1; i < table.getRows().getCount(); i++) {
            TableRow row = table.getRows().get(i);
            JSONObject textList = new JSONObject();
            //遍历每行中的单元格
            for (int j = 0; j < row.getCells().getCount(); j++) {
                TableCell cell = row.getCells().get(j);
                //遍历单元格中的段落
                String wordText = "";
                for (int k = 0; k < cell.getParagraphs().getCount(); k++) {
                    Paragraph paragraph = cell.getParagraphs().get(k);
                    wordText = paragraph.getText();//获取文本内容
                }
                if (j == 0) {
                    textList.put("name", wordText);
                } else if (j == 1) {
                    textList.put("duration", wordText);
                } else if (j == 2) {
                    textList.put("accountID", wordText);
                }
            }
            s.add(textList.toString());
        }
        return s;
    }

    public static JSONArray readTrainWord(String filePath) {
        //加载Word测试文档
        Document docx = new Document();
        docx.loadFromFile(filePath);
        //获取第一节
        Section section = docx.getSections().get(0);
        //获取第一个表格
        ITable table = section.getTables().get(0);
        //创建List
        JSONArray s = new JSONArray();
        //遍历表格中的行
        for (int i = 1; i < table.getRows().getCount(); i++) {
            TableRow row = table.getRows().get(i);
            JSONObject textList = new JSONObject();
            //遍历每行中的单元格
            for (int j = 0; j < row.getCells().getCount(); j++) {
                TableCell cell = row.getCells().get(j);
                //遍历单元格中的段落
                String wordText = "";
                for (int k = 0; k < cell.getParagraphs().getCount(); k++) {
                    Paragraph paragraph = cell.getParagraphs().get(k);
                    wordText = paragraph.getText();//获取文本内容
                    //遍历段落中的所有子对象
                    for (int x = 0; x < paragraph.getChildObjects().getCount(); x++) {
                        Object object = paragraph.getChildObjects().get(x);
                        //判定对象是否为图片
                        if (object instanceof DocPicture) {
                            //获取图片
                            DocPicture picture = (DocPicture) object;
                            try {
                                wordText = CommonUtils.savePic(picture.getImage(), "1");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (j == 0) {
                    textList.put("title", wordText);
                } else if (j == 1) {
                    textList.put("picPath", wordText);
                } else if (j == 2) {
                    textList.put("issuer", wordText);
                } else if (j == 3) {
                    textList.put("courses", wordText);
                } else if (j == 4) {
                    textList.put("startTime", wordText);
                } else if (j == 5) {
                    textList.put("endTime", wordText);
                } else if (j == 6) {
                    textList.put("address", wordText);
                } else if (j == 7) {
                    textList.put("remark", wordText);
                }
            }
            s.add(textList.toString());
        }
        return s;
    }

    public static List<String> readCourseExcel(String filePath) throws IOException {
        File file = new File(filePath);
        InputStream in = null;
        // 读取整个Excel
        XSSFWorkbook sheets = null;
        if (!file.exists()) {
            try {
                throw new Exception("文件不存在!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            in = new FileInputStream(file);
            sheets = new XSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
        // 获取第一个表单Sheet
        assert sheets != null;//断言表格不为空
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        //创建List
        List<String> s = new ArrayList<>();
        // 循环获取每一行数据
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            JSONObject textList = new JSONObject();
            XSSFRow row = sheetAt.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                XSSFCell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equals("")) {
                    continue;
                }
                if (j == 0) {
                    textList.put("name", cell.toString());
                } else if (j == 1) {
                    textList.put("duration", cell.toString());
                } else if (j == 2) {
                    textList.put("accountID", cell.toString());
                }
            }
            s.add(textList.toString());
        }
        return s;
    }

    public static JSONArray readTrainExcel(String filePath) throws Exception {
//        Map<String, PictureData> picMap = TestReadFile.getPictureFromExcel(filePath);
        File file = new File(filePath);
        InputStream in = null;
        // 读取整个Excel
        XSSFWorkbook sheets = null;
        if (!file.exists()) {
            try {
                throw new Exception("文件不存在!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            in = new FileInputStream(file);
            sheets = new XSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
        // 获取第一个表单Sheet
        assert sheets != null;//断言表格不为空
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        Map<String, XSSFPictureData> picMap = TestReadFile.getPictures(sheetAt);
        System.out.println("picMap:" + picMap);
        //创建List
        JSONArray s = new JSONArray();
        // 循环获取每一行数据
        for (int i = 2; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            JSONObject textList = new JSONObject();
            XSSFRow row = sheetAt.getRow(i);
            try {
                for (int j = 0; j <= row.getPhysicalNumberOfCells(); j++) {
                    XSSFCell cell = row.getCell(j);
                    if (j == 0) {
                        textList.put("title", cell.toString());
                    } else if (j == 1) {
                        String picPath = TestReadFile.writeImg(picMap, i, 1);
                        textList.put("picPath", picPath);
                    } else if (j == 2) {
                        textList.put("issuer", cell.toString());
                    } else if (j == 3) {
                        textList.put("courses", cell.toString());
                    } else if (j == 4) {
                        if ("NUMERIC".equals(cell.getCellType().toString())) {//判断cell类型是否为numeric number
                            textList.put("startTime", cell.getDateCellValue());
                        } else {
                            textList.put("startTime", cell.toString());
                        }
                    } else if (j == 5) {
                        if ("NUMERIC".equals(cell.getCellType().toString())) {//判断cell类型是否为numeric number
                            textList.put("endTime", cell.getDateCellValue());
                        } else {
                            textList.put("endTime", cell.toString());
                        }
                    } else if (j == 6) {
                        textList.put("address", cell.toString());
                    } else if (j == 7) {
                        textList.put("remark", cell.toString());
                    }
                }
            } catch (Exception e) {
                break;
            }
            s.add(textList.toString());
        }
        System.out.println("EEEEEEEEEEEEEE:" + s);
        return s;
    }

    public static List<String> readUserExcel(String filePath) throws IOException {
        File file = new File(filePath);
        InputStream in = null;
        // 读取整个Excel
        XSSFWorkbook sheets = null;
        if (!file.exists()) {
            try {
                throw new Exception("文件不存在!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            in = new FileInputStream(file);
            sheets = new XSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
        // 获取第一个表单Sheet
        assert sheets != null;//断言表格不为空
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        //创建List
        List<String> s = new ArrayList<>();
        // 循环获取每一行数据
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            JSONObject textList = new JSONObject();
            XSSFRow row = sheetAt.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                XSSFCell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equals("")) {
                    continue;
                }
                if (j == 0) {
                    textList.put("accountID", cell.toString());
                }
            }
            s.add(textList.toString());
        }
        return s;
    }
}