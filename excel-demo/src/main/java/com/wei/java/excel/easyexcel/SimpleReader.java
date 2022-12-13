package com.wei.java.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.wei.java.excel.easyexcel.model.DemoData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author buhuan.wang
 * @since 2022/12/13
 */
public class SimpleReader {
    private static String path = "./POI-easyExcel/";
    private static String cellValue = "abcdefghij";

    public static void main(String[] args) {

        String filename = path + "simpleRead.xlsx";
        SimpleReaderListener<DemoData> simpleReaderListener = new SimpleReaderListener<>();
        System.out.println("============ start =============");
        printMemory();

        readExcel(filename, DemoData.class, simpleReaderListener);
        List<DemoData> result = simpleReaderListener.getResult();
        System.out.println("============ end =============");
        printMemory();
        System.out.println(result.size());

    }

    /**
     * 读取Excel（一个sheet）
     *
     * @param clazz   实体类
     * @param sheetNo sheet序号
     * @return 返回实体列表(需转换)
     */
    public static <T> void readExcel(String filename, Class<T> clazz, int sheetNo, SimpleReaderListener<T> simpleReaderListener) {

        ExcelReader excelReader = getReader(filename, clazz, simpleReaderListener);
        if (excelReader == null) {
            return;
        }

        try {
            ReadSheet readSheet = EasyExcel.readSheet(sheetNo).build();
            excelReader.read(readSheet);
        } finally {
            excelReader.finish();
        }


        return;
    }


    /**
     * 读取Excel（多个sheet可以用同一个实体类解析）
     *
     * @param clazz 实体类
     * @return 返回实体列表(需转换)
     */
    public static <T> void readExcel(String filename, Class<T> clazz, SimpleReaderListener<T> simpleReaderListener) {

        ExcelReader excelReader = getReader(filename, clazz, simpleReaderListener);
        if (excelReader == null) {
            return;
        }

        try {
            List<ReadSheet> readSheetList = excelReader.excelExecutor().sheetList();

            int sheetNum = 0;
            for (ReadSheet readSheet : readSheetList) {
                System.out.println("--- read sheet no: " + (++sheetNum));
                excelReader.read(readSheet);
            }
        } finally {
            excelReader.finish();
        }

        return;
    }

    /**
     * @param excelListener
     */
    private static <T> ExcelReader getReader(String filename, Class<T> clazz, ReadListener excelListener) {
        try {
            if (filename == null || (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
                return null;
            }

            File file = new File(filename);
            FileInputStream fis = null;
            InputStream inputStream = null;
            try {
                fis = new FileInputStream(file);
                inputStream = new BufferedInputStream(fis);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                if (null != fis) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }

            ExcelReader excelReader = EasyExcel.read(inputStream, clazz, excelListener).build();

//            inputStream.close();

            return excelReader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
