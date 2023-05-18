package com.wei.java.io.stream;

import java.io.*;

public class FileBufferReadWriteDemo {
    public static void main(String[] args) {
        testFileReaderWriter();
        testBufferFileReaderWriter();
    }

    public static void testFileReaderWriter() {
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
        try (FileReader testFileReader = new FileReader(testFile)) {
            char[] chars = new char[1024];
            while (true) {
                int readN = testFileReader.read(chars);
                if (readN == -1) {
                    break;
                }
                System.out.println("file reader char");
                for (int i = 0; i < readN; i++) {
                    System.out.print((char) chars[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter testFileWriter = new FileWriter(testFile)) {
            char[] writebytes = new char[1024];
            writebytes[0] = 'a';
            writebytes[1] = 'b';
            testFileWriter.write(writebytes, 0, 2);
            testFileWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void testBufferFileReaderWriter() {
        System.out.println("======================= buffer reader writer =============");
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
        try (BufferedReader testFileReader = new BufferedReader(new FileReader(testFile))) {
            String inputline;
            while (true) {
                inputline = testFileReader.readLine();
                if (inputline == null) {
                    break;
                }
                System.out.println("file reader line");
                System.out.println(inputline);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter testFileWriter = new BufferedWriter(new FileWriter(testFile))) {
            String writeline = "hello";
            testFileWriter.write(writeline);
            testFileWriter.newLine();
            testFileWriter.write(writeline);
            testFileWriter.newLine();
            testFileWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
