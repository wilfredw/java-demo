package com.wei.java.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityRandom {
    public static void main(String[] args) {
        SecureRandom random1 = null;
        try {
            random1 = SecureRandom.getInstance("SHA1PRNG");

            SecureRandom random2 = SecureRandom.getInstance("SHA1PRNG");

            for (int i = 0; i < 5; i++) {
                System.out.println(random1.nextInt() + " != " + random2.nextInt());
                System.out.println(random1.nextDouble() + " != " + random2.nextDouble());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
