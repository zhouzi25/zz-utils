package com.zz.enumerate;

public enum TimeFormat {
    /**
     * 时间戳
     */
    Timestamp("0"),
    /**
     * 日期格式  yyyy
     */
    Y("yyyy"),
    /**
     * 日期格式  yyyy-MM
     */
    Ym("yyyy-MM"),
    /**
     * 日期格式  yyyy-MM-dd
     */
    Ymd("yyyy-MM-dd"),
    /**
     * 日期格式  yyyy-MM-dd hh
     */
    YmdH("yyyy-MM-dd hh"),
    /**
     * 日期格式  yyyy-MM-dd hh:mm
     */
    YmdHm("yyyy-MM-dd hh:mm"),
    /**
     * 日期格式  yyyy-MM-dd hh:mm:ss
     */
    YmdHms("yyyy-MM-dd hh:mm:ss");







    private Object timeFormatValue;



    /**
     *调用构造方法
     * @param timeFormat 选择该方法
     */
    private TimeFormat(String timeFormat){
        this.timeFormatValue = timeFormat;
    }


    /**
     * 获取当前选择时间格式
     * @return
     */
    public String getValue()
    {
        return timeFormatValue.toString();
    }

}
