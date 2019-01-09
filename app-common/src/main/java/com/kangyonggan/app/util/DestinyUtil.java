package com.kangyonggan.app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 生辰八字
 *
 * @author kangyonggan
 * @date 7/11/17
 */
public final class DestinyUtil {
    /**
     * 天干，10个
     */
    private static final String[] TIAN_GAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

    /**
     * 地支，12个
     */
    private static final String[] DI_ZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    /**
     * 生肖，12个
     */
    private static final String[] SHENG_XIAO = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    /**
     * 甲子，60个
     */
    private static final String[] JIA_ZI = {
            "丙寅", "戊寅", "庚寅", "壬寅", "甲寅",
            "丁卯", "己卯", "辛卯", "癸卯", "乙卯",
            "戊辰", "庚辰", "壬辰", "甲辰", "丙辰",
            "己巳", "辛巳", "癸巳", "乙巳", "丁巳",
            "庚午", "壬午", "甲午", "丙午", "戊午",
            "辛未", "癸未", "乙未", "丁未", "己未",
            "壬申", "甲申", "丙申", "戊申", "庚申",
            "癸酉", "乙酉", "丁酉", "己酉", "辛酉",
            "甲戌", "丙戌", "戊戌", "庚戌", "壬戌",
            "乙亥", "丁亥", "己亥", "辛亥", "癸亥",
            "丙子", "戊子", "庚子", "壬子", "甲子",
            "丁丑", "己丑", "辛丑", "癸丑", "乙丑"
    };

    private final static int[] DAY_ARR = new int[]{20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22};

    /**
     * 12星座
     */
    private final static String[] CONSTELLATION_ARR = new String[]{"摩羯座",
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "摩羯座"};

    /**
     * 12属相
     */
    private final static String[] SHU_XIANG = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    private DestinyUtil() {

    }

    /**
     * 计算星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getXingZuo(int month, int day) {
        return day < DAY_ARR[month - 1] ? CONSTELLATION_ARR[month - 1] : CONSTELLATION_ARR[month];
    }

    /**
     * 计算属相
     *
     * @param year
     * @return
     */
    public static String getShuXiang(int year) {
        if (year < 1900) {
            return "未知";
        }
        int start = 1900;
        return SHU_XIANG[(year - start) % SHU_XIANG.length];
    }

    /**
     * 获取生辰八字(阴历)
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     * @throws Exception
     */
    public static String getEightWord4Lunar(int year, int month, int day, int hour) throws Exception {
        try {
            String res = CalendarUtil.lunarToSolar(LocalDate.of(year, month, day).format(DateTimeFormatter.BASIC_ISO_DATE));
            return getEightWord(Integer.parseInt(res.substring(0, 4)), Integer.parseInt(res.substring(4, 6)), Integer.parseInt(res.substring(6, 8)), hour);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取生辰八字
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    public static String getEightWord(int year, int month, int day, int hour) {
        return getYearColumn(year) + getMonthColumn(year, month) + getDayColumn(year, month, day) + getHourColumn(year, month, day, hour);
    }

    /**
     * 计算年柱
     * <p/>
     * 年柱 = 天干 + 地支
     *
     * @param year
     * @return
     */
    public static String getYearColumn(int year) {
        return getTianGan(year - 3) + getDiZhi(year - 3);
    }

    /**
     * 计算天干
     *
     * @param num
     * @return
     */
    private static String getTianGan(int num) {
        return TIAN_GAN[getTianGanIndex(num)];
    }

    /**
     * 计算天干下标
     *
     * @param num
     * @return
     */
    private static int getTianGanIndex(int num) {
        return (num % TIAN_GAN.length + 9) % 10;
    }

    /**
     * 计算地支
     *
     * @param num
     * @return
     */
    private static String getDiZhi(int num) {
        return DI_ZHI[getDiZhiIndex(num)];
    }

    /**
     * 计算地支下标
     *
     * @param num
     * @return
     */
    private static int getDiZhiIndex(int num) {
        return (num % DI_ZHI.length + 9) % 12;
    }

    /**
     * 计算月柱
     *
     * @param year
     * @param month
     * @return
     */
    private static String getMonthColumn(int year, int month) {
        return JIA_ZI[getTianGanIndex(year - 3) % 5 + (month - 1) * 5];
    }

    /**
     * 计算日柱
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getDayColumn(int year, int month, int day) {
        int remainder = ((year - 1) * 5 + (year - 1) / 4 + LocalDate.of(year, month, day).getDayOfYear()) % 60;
        return getTianGan(remainder) + getDiZhi(remainder);
    }

    /**
     * 计算时柱
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    private static String getHourColumn(int year, int month, int day, int hour) {
        int remainder = ((year - 1) * 5 + (year - 1) / 4 + LocalDate.of(year, month, day).getDayOfYear()) % 60;
        int col = getTianGanIndex(remainder) + 1;
        int row = (hour + 3) / 2 % 13;

        return TIAN_GAN[(col + row * 5) % 10] + DI_ZHI[row - 1];
    }

    /**
     * 获取生肖
     *
     * @param year
     * @return
     */
    public static String getShengXiao(int year) {
        return SHENG_XIAO[(getDiZhiIndex(year - 3) + 2) % 12];
    }

    /**
     * 根据八字获取五行
     *
     * @param baZi
     * @return
     */
    public static String getWuXing(String baZi) {
        StringBuilder wuXing = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            wuXing.append(getTianGanWuXing(baZi.substring(2 * i, 2 * i + 1)));
            wuXing.append(getDiZhiWuXing(baZi.substring(2 * i + 1, 2 * i + 2)));
        }

        return wuXing.toString();
    }

    /**
     * 获取天干对应的五行
     *
     * @param tianGan
     * @return
     */
    public static String getTianGanWuXing(String tianGan) {
        if ("甲乙".indexOf(tianGan) > -1) {
            return "木";
        } else if ("丙丁".indexOf(tianGan) > -1) {
            return "火";
        } else if ("戊己".indexOf(tianGan) > -1) {
            return "土";
        } else if ("庚辛".indexOf(tianGan) > -1) {
            return "金";
        } else if ("壬癸".indexOf(tianGan) > -1) {
            return "水";
        }
        return tianGan;
    }

    /**
     * 获取地址对应的五行
     *
     * @param diZhi
     * @return
     */
    private static String getDiZhiWuXing(String diZhi) {
        if ("寅卯".indexOf(diZhi) > -1) {
            return "木";
        } else if ("巳午".indexOf(diZhi) > -1) {
            return "火";
        } else if ("辰丑戌未".indexOf(diZhi) > -1) {
            return "土";
        } else if ("申酉".indexOf(diZhi) > -1) {
            return "金";
        } else if ("亥子".indexOf(diZhi) > -1) {
            return "水";
        }
        return diZhi;
    }

    /**
     * 计算五行缺、弱、强和圆满
     *
     * @param data
     * @return
     */
    public static String[] wuxing(String data) {
        String[] result = new String[2];
        String[] wuxing = {"金", "木", "水", "火", "土"};

        String[] que = new String[4];
        String[] ruo = new String[4];
        String[] qiang = new String[2];
        int queLen = 0;
        int ruoLen = 0;
        int qiangLen = 0;
        for (int i = 0; i < wuxing.length; i++) {
            // 统计缺五行
            if (data.indexOf(wuxing[i]) == -1) {
                que[queLen++] = wuxing[i];
            }

            // 统计弱五行
            if (getCount(data, wuxing[i]) == 1) {
                ruo[ruoLen++] = wuxing[i];
            }

            // 统计强五行
            if (getCount(data, wuxing[i]) > 2) {
                qiang[qiangLen++] = wuxing[i];
            }
        }

        if (queLen == 0) {
            // 五行圆满
            result[0] = "五行圆满";
        } else {
            // 五行有缺
            StringBuilder buff = new StringBuilder();
            for (int i = 0; i < queLen; i++) {
                buff.append(que[i]).append(" ");
            }
            result[0] = "五行缺：" + buff.toString();
        }

        if (ruoLen > 0) {
            StringBuilder buff = new StringBuilder();
            for (int i = 0; i < ruoLen; i++) {
                buff.append(ruo[i]).append(" ");
            }
            result[1] = "弱五行：" + buff.toString();
        }

        if (qiangLen > 0) {
            StringBuilder buff = new StringBuilder();
            for (int i = 0; i < qiangLen; i++) {
                buff.append(qiang[i]).append("\t");
            }
            result[2] = "强五行：" + buff.toString();
        }

        return result;
    }

    /**
     * 计算word在data中的个数
     *
     * @param data
     * @param word
     * @return
     */
    private static int getCount(String data, String word) {
        int count = 0;
        for (int i = 0; i < data.length(); i++) {
            if (word.equals(data.substring(i, i + 1))) {
                count++;
            }
        }

        return count;
    }

    /**
     * 获取运势
     *
     * @param wuXingOfRiGan 日干对应的五行
     * @param month         月
     * @return
     */
    public static String getYunShi(String wuXingOfRiGan, int month) {
        if ("木".equals(wuXingOfRiGan)) {
            if (month >= 1 && month <= 3) {
                return "必须有火助，有水更好，但忌水太多，也忌土太多。";
            } else if (month >= 4 && month <= 6) {
                return "必须有水相助，忌土太多，也忌木太多。";
            } else if (month >= 7 && month <= 9) {
                return "必须有金相助，但忌金太多，须有土、火才好，但忌水多。";
            } else if (month >= 10 && month <= 12) {
                return "必须有火相助，最好有土、水。";
            }
        } else if ("火".equals(wuXingOfRiGan)) {
            if (month >= 1 && month <= 3) {
                return "此时必为丙火或丁火，大都不错，但忌木多、土多。";
            } else if (month >= 4 && month <= 6) {
                return "必须有水相助，最喜有金。";
            } else if (month >= 7 && month <= 9) {
                return "喜有木，忌水、土多。";
            } else if (month >= 10 && month <= 12) {
                return "必须有木相助，忌有水与金多，喜有土、水、木。";
            }
        } else if ("土".equals(wuXingOfRiGan)) {
            if (month >= 1 && month <= 3) {
                return "喜有火、木，喜有金而少，忌金多、木多。";
            } else if (month >= 4 && month <= 6) {
                return "喜有水、金，忌有木。";
            } else if (month >= 7 && month <= 9) {
                return "喜有火，有木，忌金、水多。";
            } else if (month >= 10 && month <= 12) {
                return "喜有火，更喜有火又有金，喜有土、木。";
            }
        } else if ("金".equals(wuXingOfRiGan)) {
            if (month >= 1 && month <= 3) {
                return "喜有土、火，最忌没有土、金。";
            } else if (month >= 4 && month <= 6) {
                return "必须有水相助，忌木多。";
            } else if (month >= 7 && month <= 9) {
                return "喜有木、火，忌土多。";
            } else if (month >= 10 && month <= 12) {
                return "必须有火、土相助，忌无火、土反而有金、水，忌木多而无火。";
            }
        } else if ("水".equals(wuXingOfRiGan)) {
            if (month >= 1 && month <= 3) {
                return "必须有土相助，若有火，金，但忌金多。";
            } else if (month >= 4 && month <= 6) {
                return "必须有金相助，忌木多。";
            } else if (month >= 7 && month <= 9) {
                return "必须有金相助，忌土、金、水多，喜木、火。";
            } else if (month >= 10 && month <= 12) {
                return "必须有火相助，喜水多，但忌金多。";
            }
        }

        return "";
    }
}
