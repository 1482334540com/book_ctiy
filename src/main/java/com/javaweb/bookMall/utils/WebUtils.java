package com.javaweb.bookMall.utils;

public class WebUtils {
    //    将String类型都数据转换为int类型都数据
    public static int parseInt(String strInt,int defaultValue ){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            System.out.println("当前页码为空");
        }
        return defaultValue;
    }
}
