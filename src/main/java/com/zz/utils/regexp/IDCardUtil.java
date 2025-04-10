package com.zz.utils.regexp;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


public class IDCardUtil {



    /**
     * 验证身份证
     * @param idStr id
     * @return "YES" 代表合法的身份证 ,其他值代表错误信息
     */
    public static Boolean isIdCardValidate(String idStr) {
        int length = idStr.length();
        // 判断号码的长度必须为15位或18位
        if (18 != length && 15 != length) {
            System.out.println("身份证号码长度必须为18位或15位!");
            return false;
        }
        // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
        String numStr = "";
        if (length == 18) {
            numStr = idStr.substring(0, 17);
            if (!isNumber(numStr)) {
                System.out.println("18位身份证号码，前17位应该为数字!");
                return false;
            }
        }
        if (length == 15) {
            numStr = idStr.substring(0, 6) + "19" + idStr.substring(6, 15);
            if (!isNumber(numStr)) {
                System.out.println("15位身份证号码，全部应该为数字!");
                return false;
            }
        }
        // 判断出生年月是否有效
        String year = numStr.substring(6, 10);// 年份
        String month = numStr.substring(10, 12);// 月份
        String day = numStr.substring(12, 14);// 日期
        String ymd = year + "-" + month + "-" + day;
        if (!isDate(ymd)) {
            System.out.println("身份证出生日期无效!");
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(year)) > 150 || (gc.getTime().getTime() - s.parse(ymd).getTime()) < 0) {
                System.out.println("身份证生日不在有效范围!");
                return false;
            }
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(month) > 12 || Integer.parseInt(month) == 0) {
            System.out.println( "身份证月份无效!");
            return false;
        }
        if (Integer.parseInt(day) > 31 || Integer.parseInt(day) == 0) {
            System.out.println("身份证日期无效!");
            return false;
        }
        // 判断地区码是否有效
        Hashtable<String, String> areaCode = getAreaCode();
        // 如果身份证前两位的地区码不在Hashtable，则地区码有误
        if (null == areaCode.get(numStr.substring(0, 2))) {
            System.out.println("身份证地区编码错误!");
            return false;
        }
        if (!isVarifyCode(numStr, idStr)) {
            System.out.println("身份证的校验码可能错误!");
            return false;
        }
        return true;
    }

    /*
     * 判断第18位校验码是否正确 第18位校验码的计算方式： 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i =
     * 0, ... , 16 其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2 2. 用11对计算结果取模 Y = mod(S, 11) 3. 根据模的值得到对应的校验码
     * 对应关系为： Y值： 0 1 2 3 4 5 6 7 8 9 10 校验码： 1 0 X 9 8 7 6 5 4 3 2
     */
    private static boolean isVarifyCode(String numStr, String idStr) {
        String[] varifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(numStr.charAt(i))) * Integer.parseInt(wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = varifyCode[modValue];
        numStr = numStr + strVerifyCode;
        if (idStr.length() == 18) {
            return numStr.equals(idStr);
        }
        return true;
    }

    /**
     * 将所有地址编码保存在一个Hashtable中
     *
     * @return Hashtable 对象
     */
    private static Hashtable<String, String> getAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 判断字符串是否为数字,0-9重复0次或者多次
     *
     * @param strNum 数字字符串
     * @return true/false
     */
    private static boolean isNumber(String strNum) {
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(strNum);
        return isNum.matches();
    }

    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     *
     * @param strDate 日期字符串
     * @return true/false
     */
    private static boolean isDate(String strDate) {
        Pattern pattern = compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }

    /**
     * 根据身份证的号码算出当前身份证持有者的年龄
     *
     * @return
     */
    public static int countAge(String idNumber) {
        if(idNumber.length() != 18 && idNumber.length() != 15){
//            throw new IllegalArgumentException("身份证号长度错误");
            return 0;
        }
        String year;
        String yue;
        String day;
        if(idNumber.length() == 18){
            year = idNumber.substring(6).substring(0, 4);// 得到年份
            yue = idNumber.substring(10).substring(0, 2);// 得到月份
            day = idNumber.substring(12).substring(0,2);//得到日
        }else{
            year = "19" + idNumber.substring(6, 8);// 年份
            yue = idNumber.substring(8, 10);// 月份
            day = idNumber.substring(10, 12);//日
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
        String fday=format.format(date).substring(8,10);//
        int age = 0;
        if(Integer.parseInt(yue) == Integer.parseInt(fyue)){//如果月份相同
            //说明已经过了生日或者今天是生日
            if (Integer.parseInt(day) <= Integer.parseInt(fday)) {
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            } else {
                age = Integer.parseInt(fyear) - Integer.parseInt(year) - 1;
            }
        }else{

            if(Integer.parseInt(yue) < Integer.parseInt(fyue)){
                //如果当前月份大于出生月份
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            }else{
                //如果当前月份小于出生月份,说明生日还没过
                age = Integer.parseInt(fyear) - Integer.parseInt(year) - 1;
            }
        }
        System.out.println("age = " + age);
        return age;
    }

}
