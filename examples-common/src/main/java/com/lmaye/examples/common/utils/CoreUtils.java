package com.lmaye.examples.common.utils;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * -- 核心工具
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CoreUtils {

    /**
     * 校验包含大写字母的正则表达式
     */
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 获取32位的UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID object = UUID.randomUUID();
        String uuid = object.toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取字母数字的随机数
     *
     * @param length
     * @return
     */
    public static String getRandomNumberOfLettersAndNumbers(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取数字的随机数
     *
     * @param length
     * @return
     */
    public static String getRandomNumberOfNumbers(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 驼峰命名转下划线
     *
     * @param humpName
     * @return
     */
    public static String humpNameToUnderline(String humpName) {
        Matcher matcher = UPPERCASE_PATTERN.matcher(humpName);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 获取最大长度的字符串
     *
     * @param param  字符串
     * @param length 最大长度
     * @return 新字符串
     */
    public static String getMaxLengthString(String param, int length) {
        Objects.requireNonNull(param, "The intercepted string cannot be empty");

        if (param.length() > length) {
            return param.substring(0, length - 3) + "...";
        } else {
            return param;
        }
    }

    /**
     * 隐藏字符串
     *
     * @param param 字符串
     * @param start 前面显示位数
     * @param end   后面显示位数
     * @return
     */
    public static String hideString(String param, int start, int end) {
        if (Strings.isNullOrEmpty(param)) {
            return param;
        }

        if (param.length() <= start + end) {
            return param;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < (param.length() - end); i++) {
            stringBuilder.append("*");
        }
        return param.substring(0, start) + stringBuilder.toString() + param.substring(param.length() - end);
    }

    /**
     * 金额大小写转换
     *
     * @param param 分
     * @return
     */
    public static String digitUppercase(long param) {
        BigDecimal amount = new BigDecimal(param).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
        String[] fraction = {"角", "分"};
        String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[][] unit = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};
        String head = amount.doubleValue() < 0 ? "负" : "";

        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            double f1 = amount.multiply(new BigDecimal(10 * Math.pow(10, i))).doubleValue();
            s += (digit[(int) (Math.floor(f1) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }

        int integerPart = (int) Math.floor(amount.doubleValue());

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && amount.doubleValue() > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * 根据参数名获取参数值
     *
     * @param key
     * @param map
     * @return
     */
    public static String getMapValue(String key, Map<String, Object> map) {
        if (Objects.isNull(map)) {
            return "";
        }

        Object mapValue = map.get(key);
        if (!Objects.isNull(mapValue)) {
            return mapValue.toString();
        }
        return "";
    }

    public static String calindex_bytime(Integer type) {
        //获取当前时间搓
        Long dtime = System.currentTimeMillis();
//        System.currentTimeMillis();

        long index = 0;

        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        if(type == 1){//时时彩
            long dssc0;
            long dssc02;
            long dssc10;
            long dssc22;
            long dssc23;
            //00:00
            dssc0 = dtime+0*60*60*1000;
            //02:00
            dssc02 = dtime+2*60*60*1000;
            //10:00
            dssc10 = dtime+10*60*60*1000;
            //22:00
            dssc22 = dtime+22*60*60*1000;
            //23:59
            dssc23 = dtime+23*60*60*1000 + 59*60*1000;

            if (dtime < dssc02){
                index = (dtime - dssc0) / (5 * 60) + 1;
            }else if(dtime < dssc10){
                index = 25;
            }else if(dtime < dssc22){
                index = 24 + (dtime - dssc10) / (10 * 60) + 1;
            }else {
                index = 96 + (dtime - dssc22) / (5 * 60) + 1;
            }

            return sdf.format(new Date(dtime+index));
        }else if(type == 2){//北京PK10

        }else if(type == 3){//江苏快3
            long djsk3;
            long djsk322;
            //08:39
            djsk3 = dtime+8*60*60*1000 + 39*60*1000;
            //22:09
            djsk322 = dtime+22*60*60*1000 + 9*60*1000;

            if(dtime < djsk3){//今天的第一期
                index = 1;
            }else if(dtime < djsk322){
                index = (dtime - djsk3) / (10 * 60) + 2;
            }else {//明天的第一期
                index = 1;
            }
            return sdf.format(new Date(dtime+index));
        }else if(type == 4){//云南11选5
            long dyn09;
            long dyn23;

            //09:00
            dyn09 = dtime+9*60*60*1000;
            //23:00
            dyn23 = dtime+23*60*60*1000;

            if (dtime < dyn09){//今天的第一期
                index = 1;
            }else if(dtime < dyn23){
                index = (dtime - dyn09) / (10 * 60) + 2;
            }else {//明天的第一期
                index = 1;
            }

            return sdf.format(new Date(dtime+index));
        }

        return "";
    }
}
