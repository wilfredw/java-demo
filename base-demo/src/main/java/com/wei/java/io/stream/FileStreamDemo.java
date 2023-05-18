package com.wei.java.io.stream;

import java.io.*;

public class FileStreamDemo {
    public static void main(String[] args) {
        testInputOutputStream();

    }

    public static void testInputOutputStream() {

        String fileTestDirPath = "testdir/file";
        File fileTestDir = new File(fileTestDirPath);
        if (!fileTestDir.exists()) {
            fileTestDir.mkdirs();
        }
        String filename = "testfile.txt";
        String testFilePath = fileTestDirPath + File.separator + filename;
        File testFile = new File(testFilePath);
        if (!testFile.exists()) {
            try {
                testFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileInputStream testFIS = new FileInputStream(testFile)) {
            byte[] bytes = new byte[1024];
            while (true) {
                int readN = testFIS.read(bytes);
                if (readN == -1) {
                    break;
                }
                System.out.println("file input stream of byte");
                for (int i = 0; i < readN; i++) {
                    System.out.print(bytes[i]);
                    System.out.print("   ");
                }
                System.out.println();
                System.out.println("file input stream of char");
                for (int i = 0; i < readN; i++) {
                    System.out.print((char) bytes[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileOutputStream testFOS = new FileOutputStream(testFile)) {
            byte[] writebytes = new byte[1024];
            writebytes[0] = 49;
            writebytes[1] = 50;
            testFOS.write(writebytes, 0, 2);
            testFOS.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void testBufferInputOutputStream() {

        String fileTestDirPath = "testdir/file";
        File fileTestDir = new File(fileTestDirPath);
        if (!fileTestDir.exists()) {
            fileTestDir.mkdirs();
        }
        String filename = "testfile2.txt";
        String testFilePath = fileTestDirPath + File.separator + filename;
        File testFile = new File(testFilePath);
        if (!testFile.exists()) {
            try {
                testFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedInputStream testBIS = new BufferedInputStream(new FileInputStream(testFile))) {
            byte[] bytes = new byte[1024];
            while (true) {
                int readN = testBIS.read(bytes);
                if (readN == -1) {
                    break;
                }
                System.out.println("file input stream of byte");
                for (int i = 0; i < readN; i++) {
                    System.out.print(bytes[i]);
                    System.out.print("   ");
                }
                System.out.println();
                System.out.println("file input stream of char");
                for (int i = 0; i < readN; i++) {
                    System.out.print((char) bytes[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedOutputStream testBOS = new BufferedOutputStream(new FileOutputStream(testFile))) {
            byte[] writebytes = new byte[1024];
            writebytes[0] = 49;
            writebytes[1] = 50;
            testBOS.write(writebytes, 0, 2);
            testBOS.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
