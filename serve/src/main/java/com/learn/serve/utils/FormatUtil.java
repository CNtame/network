package com.learn.serve.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author liu
 * @version 1.0
 * @description
 * @createDate 2021/4/11
 */
public class FormatUtil {
    /**
     * 设置数字格式，保留有效小数位数为fractions
     *
     * @param fractions 保留有效小数位数
     * @return 数字格式
     */
    public static DecimalFormat decimalFormat(int fractions)
    {

        DecimalFormat df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(fractions);
        df.setMaximumFractionDigits(fractions);
        return df;
    }
}
