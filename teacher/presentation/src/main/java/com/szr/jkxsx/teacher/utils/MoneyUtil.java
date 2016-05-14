package com.szr.jkxsx.teacher.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 钱 展示相关
 * Created by fengruicong on 15-8-31.
 */
public class MoneyUtil {
    /**
     * 添加 ¥ 前缀
     */
    public static String moneyAddPrefix(String money) {
        return "¥" + money;
    }

    /**
     * 添加 元 后缀
     */
    public static String moneyAddSuffix(String money) {
        return money + "元";
    }

    /**
     * 当值小于分的钱向上取整
     * add 0.0044(处理的钱位数以元为单位最多4位小数)
     * 如0.004取整为0.01 , 0.0003取整为0.01
     */
    public static String moneyRoundUp2Point(BigDecimal money) {
        return format2Point(money.add(new BigDecimal("0.0044")));
    }

    /**
     * 格式化 bigdecimal 为两位小数
     * 如果为负数则返回 0.00
     */
    public static String format2Point(BigDecimal decimal) {
        if (decimal == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String decimalStr = df.format(decimal);
        return decimalStr;
    }

    public static String format2Point(float number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(number);
    }
}
