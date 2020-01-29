package com.wei.java.string;

import java.io.UnsupportedEncodingException;

public class StringDecodeDemo {
    public static void main(String[] args) {
        char tabChar = '\t';
        char spaceChar = ' ';
        char enterChar = '\r';
        char newLineChar = '\n';
        System.out.println(Integer.valueOf(tabChar));
        System.out.println(Integer.valueOf(spaceChar));
        System.out.println(Integer.valueOf(enterChar));
        System.out.println(Integer.valueOf(newLineChar));
        char chChar = '中';
        String fourUnicodeChar = "\uD840\uDC0B";
        System.out.println(fourUnicodeChar);
        try {
            System.out.println(str2Unicode(""+tabChar));
            System.out.println(str2Unicode(""+spaceChar));
            System.out.println(str2Unicode(""+enterChar));
            System.out.println(str2Unicode(""+newLineChar));
            System.out.println(str2Unicode(fourUnicodeChar));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符串转换成十六进制字符串
     */

    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    /**
     * 十六进制转换字符串
     */

    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexStr(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            // if (n<b.length-1) hs=hs+":";
        }
        return hs.toUpperCase();
    }

    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     */
    public static String str2Unicode(String strText) throws Exception {
        char c;
        String strRet = "";
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                strRet += "//u" + strHex;
            } else {
                // 低位在前面补00
                strRet += "//u00" + strHex;
            }
        }
        return strRet;
    }

    /**
     * unicode的String转换成String的字符串
     */
    public static String unicode2Str(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    public static final String encodeHex(byte[] bytes) {
        StringBuffer buff = new StringBuffer(bytes.length * 2);
        String b;
        for (int i = 0; i < bytes.length; ++i) {
            b = Integer.toHexString(bytes[i]);
            // byte是两个字节的，而上面的Integer.toHexString会把字节扩展为4个字节
            buff.append(b.length() > 2 ? b.substring(6, 8) : b);
            buff.append(" ");
        }
        return buff.toString();
    }

    public static String custom_trim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ((st < len) && (chars[st] == c)) {
            st++;
        }
        while ((st < len) && (chars[len - 1] == c)) {
            len--;
        }

        return (st > 0) && (len < chars.length) ? str.substring(st, len) : str;

    }

    public static String custom_ltrim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ((st < len) && (chars[st] == c)) {
            st++;
        }
        return (st > 0) ? str.substring(st, len) : str;
    }

    public static String custom_rtrim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ((st < len) && (chars[len - 1] == c)) {
            len--;
        }
        return (len < chars.length) ? str.substring(st, len) : str;
    }
}
