package com.example.Mapper;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetTimeTest {
    @Test
    public void test() {
        LocalDateTime NowTime= LocalDateTime.now();
        System.out.println("@"+NowTime+"@");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedNow = NowTime.format(formatter); // 格式化为字符串
        System.out.println("当前时间：" + formattedNow);
    }


}
