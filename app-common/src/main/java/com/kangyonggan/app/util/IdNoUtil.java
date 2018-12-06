package com.kangyonggan.app.util;

import com.github.ofofs.jca.annotation.Log;
import com.github.ofofs.jca.annotation.Util;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
@Util
public class IdNoUtil {

    /**
     * 身份证长度18位
     */
    private static final int ID_NO_LEN_18 = 18;

    /**
     * 每位加权因子
     */
    private static final int POWER[] = {
            7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2
    };

    /**
     * 第18位校检码
     */
    private static final String VERIFY_CODE[] = {
            "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"
    };

    /**
     * 判断是不是18位身份证
     *
     * @param idNo
     * @return
     */
    @Log
    public static boolean isIdNo18(String idNo) {
        if (StringUtils.isEmpty(idNo) || idNo.length() != ID_NO_LEN_18) {
            return false;
        }

        if (!isNumberWithX(idNo)) {
            return false;
        }

        try {
            new SimpleDateFormat("yyyyMMdd").parse(idNo.substring(6, 14));
        } catch (ParseException e) {
            return false;
        }

        int iSum = getPowerSum(idNo);
        String checkCode = getCheckCode18(iSum);
        if (!checkCode.equalsIgnoreCase(idNo.substring(ID_NO_LEN_18 - 1, ID_NO_LEN_18))) {
            return false;
        }

        return true;
    }

    /**
     * 判断是否是数字,包括X和x
     *
     * @param idNo
     * @return
     */
    private static boolean isNumberWithX(String idNo) {
        return idNo.matches("^[0-9Xx]+$");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param idNo
     * @return
     */
    private static int getPowerSum(String idNo) {
        int[] iArr = converCharToInt(idNo.toCharArray());
        int iSum = 0;
        for (int i = 0; i < POWER.length; i++) {
            iSum = iSum + iArr[i] * POWER[i];
        }
        return iSum;
    }

    /**
     * 将字符数组转换成数字数组
     *
     * @param ch 字符数组
     * @return 数字数组
     */
    private static int[] converCharToInt(char[] ch) {
        int len = ch.length;
        int[] iArr = new int[len];
        try {
            for (int i = 0; i < len; i++) {
                iArr[i] = Integer.parseInt(String.valueOf(ch[i]));
            }
        } catch (NumberFormatException e) {
            return iArr;
        }
        return iArr;
    }

    /**
     * 将power和值与11取模获得余数进行校验码判断
     *
     * @param iSum
     * @return 校验位
     */
    private static String getCheckCode18(int iSum) {
        return VERIFY_CODE[iSum % 11];
    }

}
