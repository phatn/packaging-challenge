package com.mobiquity.utils;


public class TestUtils {

    public static String getFilePathFromResource(String fileName) {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        return classLoader.getResource(fileName).getPath();
    }
}
