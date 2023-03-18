package com.wei.java.excel.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author buhuan.wang
 * @since 2022/4/25
 */
public class UserModelWriteDemo {
    private static String path = "./POI-easyExcel/";

    public static void main(String[] args) {
        createDir(path);

        String filename = "test";
        exportHSSFFile(filename);
        exportXSSFFile(filename);
    }

    private static void exportHSSFFile(String filename) {
        String fullFilename = path + filename + ".xls";
        // 1、创建一个工作簿(面向接口编程，比较方便维护)
        Workbook workbook = new HSSFWorkbook();
        exportFile(workbook, fullFilename);
    }

    private static void exportXSSFFile(String filename) {
        String fullFilename = path + filename + ".xlsx";
        // 1、创建一个工作簿(面向接口编程，比较方便维护)
        Workbook workbook = new XSSFWorkbook();
        exportFile(workbook, fullFilename);
    }

    private static void exportFile(Workbook workbook, String filename) {
        // 2、创建一个工作表
        Sheet sheet = workbook.createSheet();
        // 3、创建一个行
        Row row1 = sheet.createRow(0);
        // 4、创建一个单元格(1,1)
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("姓名");
        // (1,2)
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("时间");

        // 第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("晓江");
        // (2,2)
        Cell cell22 = row2.createCell(1);
        String date = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(date);

        try {
            // 生成一张表（IO流）  03边的就是使用xls结尾
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            // 输出
            workbook.write(fileOutputStream);
            // 关闭流
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("生成完毕！");
    }

    private static void createDir(String path) {
        File folder = new File(path);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
            System.out.println("创建文件夹");
        } else {
            System.out.println("文件夹已存在");
        }
    }

}
