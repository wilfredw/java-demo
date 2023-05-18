package com.wei.java.io.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferReaderInDemo {
    public static void main(String[] args) {
        testInputInt();
        testInputChar();
        testInputByteArray();

        testBufferedReaderInputInt();
        testBufferedReaderInputChar();
        testBufferedReaderInputByteArray();
        testBufferedReaderInputLine();
    }

    public static void testInputInt() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        int input = 0;
        System.out.println("======= print int ===========");
        System.out.println("please input:");
        while (true) {
            try {
                input = inputStreamReader.read();
                if (input == -1) {
                    System.out.println("meet input end");
                    break;
                }
                System.out.println("get input: " + String.valueOf(input));
                if (input == 'q') {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void testInputChar() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        System.out.println("======= print char ===========");
        System.out.println("please input:");
        char inputChar;
        while (true) {
            try {
                inputChar = (char) inputStreamReader.read();
                if (inputChar == -1) {
                    System.out.println("meet input end");
                    break;
                }
                System.out.println("get inputChar: " + String.valueOf(inputChar));
                if (inputChar == 'q') {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void testInputByteArray() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        System.out.println("======= print char array ===========");
        System.out.println("please input:");
        char[] charArray = new char[1024];
        int readN = 0;
        while (true) {
            try {
                readN = inputStreamReader.read(charArray, 0, charArray.length);
                if (readN == -1) {
                    System.out.println("meet input end");
                    break;
                }
                for (int i = 0; i < readN; i++) {
                    System.out.println("get charArray at : " + i + " is " + String.valueOf(charArray[i]));
                }
                System.out.print("get charArray string is ");
                for (int i = 0; i < readN; i++) {
                    System.out.print(String.valueOf((char) charArray[i]));
                }
                if (charArray[0] == 'q') {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void testBufferedReaderInputInt() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);

        System.out.println("======= BufferedReader print int ===========");
        int input = 0;
        System.out.println("please input:");
        while (true) {
            try {
                input = br.read();
                if (input == -1) {
                    System.out.println("meet input end");
                    break;
                }
                System.out.println("get input: " + String.valueOf(input));
                if (input == 'q') {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void testBufferedReaderInputChar() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);

        System.out.println("======= BufferedReader print char ===========");
        System.out.println("please input:");
        char inputChar;
        while (true) {
            try {
                inputChar = (char) br.read();
                if (inputChar == -1) {
                    System.out.println("meet input end");
                    break;
                }
                System.out.println("get inputChar: " + String.valueOf(inputChar));
                if (inputChar == 'q') {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void testBufferedReaderInputByteArray() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);

        System.out.println("======= BufferedReader print char array ===========");
        System.out.println("please input:");
        char[] charArray = new char[1024];
        int readN = 0;
        while (true) {
            try {
                readN = br.read(charArray, 0, charArray.length);
                if (readN == -1) {
                    System.out.println("meet input end");
                    break;
                }
                for (int i = 0; i < readN; i++) {
                    System.out.println("get charArray at : " + i + " is " + String.valueOf(charArray[i]));
                }
                System.out.print("get charArray string is ");
                for (int i = 0; i < readN; i++) {
                    System.out.print(String.valueOf((char) charArray[i]));
                }
                if (charArray[0] == 'q') {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * please input:
     * abc
     * get charArray string is abcabc
     * get charArray string is abcq
     * get charArray string is qquit
     * get charArray string is quit
     */
    public static void testBufferedReaderInputLine() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);

        System.out.println("======= BufferedReader print line ===========");
        System.out.println("please input:");
        String line;
        int readN = 0;
        while (true) {
            try {
                line = br.readLine();
                System.out.print("get charArray string is " + line);
                if ("quit".equals(line)) {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
