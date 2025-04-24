package com.example;

import org.junit.Test;

import java.io.File;

public class FiltTest {
    @Test
    public void test() {
       final String filePath = System.getProperty("user.dir") + "/files/";
       System.out.println(filePath);
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
