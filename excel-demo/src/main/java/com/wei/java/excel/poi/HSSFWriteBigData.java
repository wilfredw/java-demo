package com.wei.java.excel.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/4/25
 */
public class HSSFWriteBigData {
    private static String path = "./POI-easyExcel/";

    /**
     * max 65535
     * invalid 65537
     */
    private static int maxRowNum = 65536;

    public static void main(String[] args) {
        try {
            // 时间
            long begin = System.currentTimeMillis();

            // 创建一个薄
            Workbook workbook = new HSSFWorkbook();
            // 创建表
            Sheet sheet = workbook.createSheet();
            // 写入数据
            for (int rowNum = 0; rowNum < maxRowNum; rowNum++) {
                Row row = sheet.createRow(rowNum);
                for (int cellNum = 0; cellNum < 10; cellNum++) {
                    Cell cell = row.createCell(cellNum);
                    cell.setCellValue(cellNum);
                }
            }
            System.out.println("over");
            FileOutputStream outputStream = new FileOutputStream(path + "testWrite03BigData.xls");
            workbook.write(outputStream);
            outputStream.close();
            long end = System.currentTimeMillis();
            System.out.println((double) (end - begin) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
