package com.wei.java.io.system;

import java.util.Scanner;

public class ScannerInDemo {
    public static void main(String[] args) {
        testScanner();
        testScannerInt();
        testScannerDouble();
        testScannerBigInt();
    }

    /**
     * Please input:
     * aaa
     * scanner next: aaa
     * aa bb c
     * scanner next: aa
     * scanner next: bb
     * scanner next: c
     * quit
     * scanner next: quit
     * Please input for line:
     * scanner next line:
     * aaa bbb ccc
     * scanner next line: aaa bbb ccc
     * quit
     * scanner next line: quit
     */
    public static void testScanner() {
        System.out.println("Please input: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!scanner.hasNext()) {
                break;
            }
            String inputStr = scanner.next();
            System.out.println("scanner next: " + inputStr);
            if ("quit".equals(inputStr)) {
                break;
            }
        }

        System.out.println("Please input for line: ");
        while (true) {
            String inputStr = scanner.nextLine();
            System.out.println("scanner next line: " + inputStr);
            if ("quit".equals(inputStr)) {
                break;
            }
        }
    }

    /**
     * Please input int:
     * 1
     * scanner next int: 1
     * 2
     * scanner next int: 2
     * 1 2 3
     * scanner next int: 1
     * scanner next int: 2
     * scanner next int: 3
     * 11 22 33 44
     * scanner next int: 11
     * scanner next int: 22
     * scanner next int: 33
     * scanner next int: 44
     */
    public static void testScannerInt() {
        System.out.println("Please input int: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!scanner.hasNext()) {
                break;
            }
            Integer inputStr = scanner.nextInt();
            System.out.println("scanner next int: " + inputStr);
            if (inputStr.equals(0)) {
                break;
            }
        }

    }

    /**
     * Please input double:
     * 1.2
     * scanner next double: 1.2
     * 1.2 1.3 19.123
     * scanner next double: 1.2
     * scanner next double: 1.3
     * scanner next double: 19.123
     * 0.5
     * scanner next double: 0.5
     */
    public static void testScannerDouble() {
        System.out.println("Please input double: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!scanner.hasNext()) {
                break;
            }
            Double input = scanner.nextDouble();
            System.out.println("scanner next double: " + String.valueOf(input));
            if (input < 1 && input > 0) {
                break;
            }
        }

    }

    public static void testScannerBigInt() {
        System.out.println("Please input big int: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!scanner.hasNext()) {
                break;
            }
            Double input = scanner.nextDouble();
            System.out.println("scanner next double: " + String.valueOf(input));
            if (input < 1 && input > 0) {
                break;
            }
        }

    }
}
