package com.example.teamcity.api.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    private static final int LENGTH10 = 10;

    public static String getString() {
        return "test_" + RandomStringUtils.randomAlphabetic(LENGTH10);
    }

    public static String getStringChars(int Chars) {
        return "test_" + RandomStringUtils.randomAlphabetic(Chars);
    }
    public static String getStringSpecialChars() {
        return "test_" + RandomStringUtils.random(LENGTH10, false, false);
    }

    public static String getStringStartsSpecialChars() {
        return RandomStringUtils.random(LENGTH10, false, false);
    }
}
