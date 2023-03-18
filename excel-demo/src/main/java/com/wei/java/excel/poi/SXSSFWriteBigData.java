package com.wei.java.excel.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2022/4/25
 */
public class SXSSFWriteBigData {
    private static String path = "./POI-easyExcel/";
    private static int maxRowNum = 100000;
    private static String cellValue = "abcdefghij";

    public static void main(String[] args) {
        try {
            // 时间
            long begin = System.currentTimeMillis();

            // 创建一个薄
            Workbook workbook = new SXSSFWorkbook();
            // 创建表
            Sheet sheet = workbook.createSheet();
            // 写入数据
            for (int rowNum = 0; rowNum < maxRowNum; rowNum++) {
                Row row = sheet.createRow(rowNum);
                for (int cellNum = 0; cellNum < 10; cellNum++) {
                    Cell cell = row.createCell(cellNum);
                    cell.setCellValue(cellValue + cellNum);
                }
            }
            System.out.println("over");
            FileOutputStream outputStream = new FileOutputStream(path + "testWrite07BigDataS.xlsx");
            workbook.write(outputStream);
            outputStream.close();
            printMemory();
            // 清除临时文件！
            ((SXSSFWorkbook) workbook).dispose();
            long end = System.currentTimeMillis();
            System.out.println("time:" + (double) (end - begin) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printMemory() {
        //当前JVM占用的内存总数(M)
        double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);

        //JVM最大可用内存总数(M)

        double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);

        //JVM空闲内存(M)

        double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);

        //可用内存内存(M)

        double mayuse = (max - total + free);

        //已经使用内存(M)

        double used = (total - free);

        System.out.println("max available: " + max);
        System.out.println("total require: " + total);
        System.out.println("free : " + free);
        System.out.println("may use: " + mayuse);
        System.out.println("used: " + used);
    }
}
