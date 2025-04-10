package com.zz.utils.date;

import com.zz.enumerate.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 */
public class ZwDateUtils {

    /**
     * 日期时间转换为字符串 线程不安全
     *
     * @param date       时间
     * @param timeFormat 转换字符格式
     * @return
     */
    public static String DateToReplaceString(Date date, TimeFormat timeFormat) {
        if (timeFormat.getIntValue()==0) {  //转化为0为时间戳
            return String.valueOf(date.getTime());
        } else {
            return new SimpleDateFormat(timeFormat.getStringValue()).format(date);
        }
    }

    /**
     * 字符串转换成日期时间  线程不安全
     *
     * @param dateString 日期字符串
     * @param timeFormat 转换时间类型
     * @return date     日期
     */
    public static Date StringReplaceDate(String dateString, TimeFormat timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat.getStringValue());
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 获取当天开始时间字符串
     */
    public static String ToDayStartTime() {
        return LocalDate.now()+" 00:00:00";
    }
    /**
     * 获取当天结束时间字符串
     */
    public static String ToDayEndTime() {
        return LocalDate.now()+" 23:59:59";
    }

    public static void main(String[] args) {
       //根获取
        String dateTimeStr = "2023/10/01";
        LocalDate date = LocalDate.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        LocalDateTime localDateTime = new LocalDateTime();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        System.out.println(date); // 输出: 2023-10-01T15:30
    }
}
