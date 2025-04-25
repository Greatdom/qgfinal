package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String getTime(){
        LocalDateTime NowTime= LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return NowTime.format(formatter); // 格式化为字符串
    }
    //判定headTime是否前于hindTime，如果是则返回true
    public static boolean IsHeadTimeBefore(String head,String hind){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime headTime = LocalDateTime.parse(head, formatter);
        LocalDateTime hindTime = LocalDateTime.parse(hind, formatter);
        return headTime.isBefore(hindTime);
    }
}
