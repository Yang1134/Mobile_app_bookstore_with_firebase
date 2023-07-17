package com.example.lab2;

import java.util.Random;

public class randomisbn {

    public static final String alphanummeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateNewRandomString(int length) {
        char[] random;
        Random randomgenerator =new Random();
        random = new char[length];
        for (int idx = 0; idx < random.length; ++idx)
            random[idx] = alphanummeric.charAt(randomgenerator.nextInt(alphanummeric.length()));
        return new String(random);
    }
}
