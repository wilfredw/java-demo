package com.wei.java.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author buhuan.wang
 * @since 2022/5/19
 */
public class SimpleTest {
    @Test
    public void truncateStr() {

        String target = StringUtils.truncate("abcdefghijklmno", 1, 10); //= "bcdefghijk"
        System.out.println(target);
        target = StringUtils.truncate("abcdefghijklmno", 2, 10); // = "cdefghijkl"
        System.out.println(target);
        target = StringUtils.truncate("abcdefghijklmno", 3, 10); // = "defghijklm"
        System.out.println(target);
        System.out.println("=== test truncate 0 width");
        target = StringUtils.truncate("abcdefghijklmno", 0, 3); // = "defghijklm"
        System.out.println(target);
        target = StringUtils.truncate("abcdefghijklmno", 0, 14); // = "defghijklm"
        System.out.println(target);
        target = StringUtils.truncate("abcdefghijklmno", 0, 15); // = "defghijklm"
        System.out.println(target);
        target = StringUtils.truncate("abcdefghijklmno", 0, 16); // = "defghijklm"
        System.out.println(target);
        target = StringUtils.truncate("abcdefghijklmno", 0, 100); // = "defghijklm"
        System.out.println(target);

        String sourceUri = "abcdefghij12345";
        System.out.println("===== test substring " + sourceUri);
        System.out.println(sourceUri.substring(0, 1));
        System.out.println(sourceUri.substring(0, 2));
        System.out.println(sourceUri.substring(0, sourceUri.length() - 1));
        System.out.println(sourceUri.substring(0, sourceUri.length()));
        //System.out.println(sourceUri.substring(0, sourceUri.length() + 1)); // error
        System.out.println(sourceUri.substring(0));
        System.out.println("=== test StringUtils.truncate");
        System.out.println(StringUtils.truncate(sourceUri, 10, 3));
        System.out.println(StringUtils.truncate(sourceUri, 10, 4));
        System.out.println(StringUtils.truncate(sourceUri, 10, 5));
        System.out.println(StringUtils.truncate(sourceUri, 10, 6));
        System.out.println(StringUtils.truncate(sourceUri, 10, 20));
    }
}
