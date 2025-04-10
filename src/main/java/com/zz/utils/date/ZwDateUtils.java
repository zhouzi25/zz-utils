package com.zz.utils.date;

import com.zz.enumerate.TimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 时间工具类
 */
public class ZwDateUtils {

    /**
     * 日期时间转换为字符串
     *
     * @param date       时间
     * @param timeFormat 转换字符格式
     * @return
     */
    public static String DateToReplaceString(Date date, TimeFormat timeFormat) {
        if (timeFormat.getValue().equals("0")) {  //转化为0为时间戳
            return String.valueOf(date.getTime());
        } else {
            return new SimpleDateFormat(timeFormat.getValue()).format(date);
        }
    }

    /**
     * 字符串转换成日期时间
     *
     * @param dateString 日期字符串
     * @param timeFormat 转换时间类型
     * @return
     */
    public static Date StringReplaceDate(String dateString, TimeFormat timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat.getValue());
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转换成日期时间
     *
     * @param dateString 日期字符串
     * @param timeFormat 转换时间类型
     * @return
     */
    public static String ToDayStartTime(String dateString, TimeFormat timeFormat) {
        // 获取当前日期
        LocalDate nowDate = LocalDate.now();
        // 设置零点作为当天开始时间
                LocalDateTime beginTime = LocalDateTime.of(nowDate, LocalTime.MIN);
        // 格式化开始时间
//                String time1 = beginTime.format(TimeFormat.YmdHms.getValue());

        // 设置23:59:59作为当天结束时间
//                LocalDateTime endTime = LocalDateTime.of(nowDate, LocalTime.MAX);
        // 格式化结束时间
//                String time2 = dtf.format(endTime);
        return "";
    }
}
