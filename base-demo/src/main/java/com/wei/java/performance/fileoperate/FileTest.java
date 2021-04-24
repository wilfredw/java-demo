package com.wei.java.performance.fileoperate;

import com.wei.java.util.SystemOutUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件相关操作测试性能
 *
 * @author buhuan.wang
 * @since 2021/4/24
 */
public class FileTest {
    private static String lock = "TestMainLock";

    public static void testFileWrite() {
        File file = new File("./test.out");
        FileOutputStream fos = null;
        try {
            file.deleteOnExit();
            file.createNewFile();
            fos = new FileOutputStream(file);


            for (int y = 0; y < 100; y++) {
                for (int i = 0; i < 10000; ++i) {
                    String content = "Hi " + i;
                    synchronized (lock) {
                        SystemOutUtil.println("this is " + i + " times.");
                        fos.write(content.getBytes("UTF-8"));
                    }

                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
