package com.wei.java.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wei.java.excel.easyexcel.model.DemoData;

import java.util.List;

/**
 * @author buhuan.wang
 * @since 2022/4/25
 */
public class SimpleWrite {
    private static String path = "./POI-easyExcel/";
    private static String cellValue = "abcdefghij";

    private static List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 100000; i++) {
            DemoData data = new DemoData();
            data.setString(cellValue + 1);
            data.setDate(cellValue + 1);
            data.setDoubleData(cellValue + 1);
            data.setValue4(cellValue + 1);
            data.setValue5(cellValue + 1);
            data.setValue6(cellValue + 1);
            data.setValue7(cellValue + 1);
            data.setValue8(cellValue + 1);
            data.setValue9(cellValue + 1);
            data.setValue10(cellValue + 1);
            list.add(data);
        }
        return list;
    }

    public static void main(String[] args) {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = path + "easy-simpleWrite1-" + System.currentTimeMillis() + ".xls";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class)
//                .sheet("模板")
//                .doWrite(() -> {
//                    // 分页查询数据
//                    return data();
//                });

        // 写法2
        fileName = path + "simpleWrite2-" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

        // 时间
        long begin = System.currentTimeMillis();

        // 写法3
        fileName = path + "simpleWrite3-" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        printMemory();
        long end = System.currentTimeMillis();
        System.out.println("time:" + (double) (end - begin) / 1000);
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
