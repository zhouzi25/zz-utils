package com.zz.utils.regexp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUtils {
    /**
     * 判断是否是座机号码
     * @param phone
     * @return
     */
    public static boolean isCall(String phone){
        //正则表达式
        String regex = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";
        Pattern pattern = Pattern.compile(regex);    // 编译正则表达式
        Matcher matcher = pattern.matcher(phone);    // 创建给定输入模式的匹配器
        boolean bool = matcher.matches();
        if(bool) { // 如果验证通过
            System.out.println("输入的电话号码格式正确。");
        } else {
            System.out.println("输入的电话号码无效，格式不正确。");
        }
        return  bool;
    }  /**
     * 判断是否是手机号码
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        //正则表达式
        String regex = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
        Pattern pattern = Pattern.compile(regex);    // 编译正则表达式
        Matcher matcher = pattern.matcher(phone);    // 创建给定输入模式的匹配器
        boolean bool = matcher.matches();
        if(bool) { // 如果验证通过
            System.out.println("输入的电话号码格式正确。");
        } else {
            System.out.println("输入的电话号码无效，格式不正确。");
        }
        return  bool;
    }

    /**
     * 验证是否为弱密码
     * @param password
     * @return
     */
    public static boolean isPassword(String password){
        String regex =    "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[`~!@#$%^&*()_+\\-=\\\\\\[\\]{}|,./;':\"<>?]).{12,20}$" ;
        Pattern pattern = Pattern.compile(regex);    // 编译正则表达式
        Matcher matcher = pattern.matcher(password);    // 创建给定输入模式的匹配器
        boolean bool = matcher.matches();
        if(bool) { // 如果验证通过
            System.out.println("输入的密码格式正确。");
        } else {
            System.out.println("输入的密码无效，格式不正确。必须由12-20位数字、字母、特殊字符组成 ");
        }
        return bool;
    }

    /**
     * 是否是身份证号
     * @param idCard
     * @return
     */
    public static  boolean isIdCard(String idCard){
        return  IDCardUtil.isIdCardValidate(idCard);
    }
}
