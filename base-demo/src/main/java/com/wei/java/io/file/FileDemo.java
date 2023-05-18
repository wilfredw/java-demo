package com.wei.java.io.file;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        String path = "testdir/aaa";
        File testDir = new File(path);
        if (!testDir.exists()) {
            testDir.mkdirs();
        }
        if (!testDir.isDirectory()) {
            throw new RuntimeException("invalid dir");
        }
        String testFilePath = path + File.separator + "aa.txt";
        File testFile = new File(testFilePath);
        if (!testFile.exists()) {
            try {
                testFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!testFile.isFile()) {
            throw new RuntimeException("invalid file");
        }
        String absolutePath = testFile.getAbsolutePath();
        String filePath = testFile.getPath();
        System.out.println("File.separator " + File.separator);
        System.out.println("File.separatorChar " + File.separatorChar);
        System.out.println("File.pathSeparator " + File.pathSeparator);
        System.out.println("File.pathSeparatorChar " + File.pathSeparatorChar);
        System.out.println("absolutePath: " + absolutePath);
        System.out.println("filePath: " + filePath);
        System.out.println("file name: " + testFile.getName());
        System.out.println("file name by last index: " +
                absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1, absolutePath.length()));
        String[] pathArray = absolutePath.split("\\" + File.separator);
        System.out.println("path array: " + JSON.toJSONString(pathArray));
        System.out.println("file name by separator: " + pathArray[pathArray.length - 1]);

    }
}
