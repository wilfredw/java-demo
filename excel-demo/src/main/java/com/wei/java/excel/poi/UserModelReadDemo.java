package com.wei.java.excel.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author buhuan.wang
 * @since 2022/4/25
 */
public class UserModelReadDemo {
    private static String path = "./POI-easyExcel/";

    public static void main(String[] args) {
        checkDir(path);

        String filename = "test";
        importHSSFFile(filename);
        importXSSFFile(filename);
    }

    private static void importHSSFFile(String filename) {
        String fullFilename = path + filename + ".xls";
        FileInputStream inputStream = null;
        // 获取文件流
        try {
            inputStream = new FileInputStream(fullFilename);
            // 1、创建一个工作簿(面向接口编程，比较方便维护)
            Workbook workbook = new HSSFWorkbook(inputStream);

            System.out.println("============= " + fullFilename);

            importFile(workbook);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private static void importXSSFFile(String filename) {
        String fullFilename = path + filename + ".xlsx";

        FileInputStream inputStream = null;
        // 获取文件流
        try {
            inputStream = new FileInputStream(fullFilename);
            // 1、创建一个工作簿(面向接口编程，比较方便维护)
            Workbook workbook = new XSSFWorkbook(inputStream);

            System.out.println("============= " + fullFilename);

            importFile(workbook);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void importFile(Workbook workbook) {
        // 2、得到表
        Sheet sheet = workbook.getSheetAt(0);
        // 3、得到行
        for (int r = 0; r < 2; r++) {
            Row row = sheet.getRow(r);
            for (int cellIndex = 0; cellIndex < 2; cellIndex++) {
                // 4、得到列
                Cell cell = row.getCell(cellIndex);

                // 读取值的时候，一定需要注意类型！
                // getStringCellValue 字符串类型
                System.out.print(cell.getStringCellValue());
                System.out.print(" , ");
            }
            System.out.println();
        }
    }

    private static void checkDir(String path) {
        File folder = new File(path);
        if (!folder.exists() && !folder.isDirectory()) {
            throw new RuntimeException("文件夹不存在");
        }
    }

}
