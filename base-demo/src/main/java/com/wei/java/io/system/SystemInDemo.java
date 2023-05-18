package com.wei.java.io.system;

import java.io.IOException;

public class SystemInDemo {
    public static void main(String[] args) {
        testInputInt();

        testInputChar();

        testInputByteArray();
    }

    public static void testInputInt() {
        int input = 0;
        System.out.println("please input:");
        while (true) {
            try {
                input = System.in.read();
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

        System.out.println("======= print char ===========");
        System.out.println("please input:");
        char inputChar;
        while (true) {
            try {
                inputChar = (char) System.in.read();
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

        System.out.println("======= print char ===========");
        System.out.println("please input:");
        byte[] byteArray = new byte[1024];
        int readN = 0;
        while (true) {
            try {
                readN = System.in.read(byteArray, 0, byteArray.length);
                if (readN == -1) {
                    System.out.println("meet input end");
                    break;
                }
                for (int i = 0; i < readN; i++) {
                    System.out.println("get byteArray at : " + i + " is " + String.valueOf(byteArray[i]));
                }
                System.out.print("get byteArray string is ");
                for (int i = 0; i < readN; i++) {
                    System.out.print(String.valueOf((char) byteArray[i]));
                }
                if (byteArray[0] == 'q') {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
