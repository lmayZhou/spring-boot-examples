package com.lmaye.examples.common.utils;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CoreUtils {

    /**
     * 校验包含大写字母的正则表达式
     */
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 获取32位的UUID
     * - 去-符号
     *
     * @return String
     */
    public static String getUuid() {
        UUID object = UUID.randomUUID();
        String uuid = object.toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取随机数(字母、数字)
     *
     * @param length 长度
     * @return String
     */
    public static String getRandomNumber(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取随机数(数字)
     *
     * @param length 长度
     * @return String
     */
    public static String getRandomNumberForNumbers(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 驼峰命名转下划线
     *
     * @param humpName 名称
     * @return String
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
     * - length - 3
     *
     * @param param  字符串
     * @param length 最大长度
     * @return String
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
     * @return String
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
     * @return String
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
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
                .replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * 根据参数名获取参数值
     *
     * @param key 键
     * @param map Map集合
     * @return String
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
}
