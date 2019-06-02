package com.lmaye.examples.common.utils;

import com.google.common.base.Strings;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 字符串操作工具类 </p>
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public abstract class StringUtils {

    private static final int INDEX_NOT_FOUND = -1;
    /**
     * 校验包含大写字母的正则表达式
     */
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 判断字符串是否为空或者空字符串
     *
     * @param str     字符串
     * @param message 异常描述
     * @return 字符串
     */
    public static String requireNonEmpty(String str, String message) {
        if (Strings.isNullOrEmpty(str)) {
            throw new NullPointerException(message);
        }
        return str;
    }

    /**
     * 生成32位的Uuid
     *
     * @return 字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生产随机字符串
     *
     * @param length 字符串长度
     * @return 字符串
     */
    public static String generateStr(int length) {
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
     * 转下划线
     *
     * @param str 字符串
     * @return 大写转下划线的字符串
     */
    public static String toUnderline(String str) {
        requireNonEmpty(str, "The transform string cannot be empty");

        Matcher matcher = UPPERCASE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 截取字符串，如果超出最大长度，则截取到最大长度-3的位数再加上“...”
     *
     * @param str    字符串
     * @param length 最大长度
     * @return 新字符串
     */
    public static String substr(String str, int length) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }

        if (str.length() <= length) {
            return str;
        }

        return str.substring(0, length - 3) + "...";
    }

    /**
     * 截取字符串，被截掉的部分用*代替
     *
     * @param str   字符串
     * @param start 前面显示位数
     * @param end   后面显示位数
     * @return 字符串
     */
    public static String substr(String str, int start, int end) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }

        if (str.length() <= start + end) {
            return str;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < (str.length() - end); i++) {
            stringBuilder.append("*");
        }
        return str.substring(0, start) + stringBuilder.toString() + str.substring(str.length() - end);
    }

    /**
     * 编码字符串<br>
     * 使用系统默认编码
     *
     * @param str 字符串
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str) {
        return bytes(str, Charset.defaultCharset());
    }

    /**
     * 编码字符串
     *
     * @param str     字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.toString().getBytes();
        }
        return str.toString().getBytes(charset);
    }

    /**
     * 解码字节码
     *
     * @param data 字符串
     * @return 解码后的字符串
     */
    public static String str(byte[] data) {
        if (data == null) {
            return null;
        }

        return new String(data, Charset.defaultCharset());
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }

    /**
     * 指定范围内查找字符串，忽略大小写<br>
     *
     * <pre>
     * StrUtil.indexOfIgnoreCase(null, *, *)          = -1
     * StrUtil.indexOfIgnoreCase(*, null, *)          = -1
     * StrUtil.indexOfIgnoreCase("", "", 0)           = 0
     * StrUtil.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
     * StrUtil.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
     * StrUtil.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
     * StrUtil.indexOfIgnoreCase("abc", "", 9)        = -1
     * </pre>
     *
     * @param str       字符串
     * @param searchStr 需要查找位置的字符串
     * @return 位置
     * @since 3.2.1
     */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        return indexOfIgnoreCase(str, searchStr, 0);
    }

    /**
     * 指定范围内查找字符串
     *
     * <pre>
     * StrUtil.indexOfIgnoreCase(null, *, *)          = -1
     * StrUtil.indexOfIgnoreCase(*, null, *)          = -1
     * StrUtil.indexOfIgnoreCase("", "", 0)           = 0
     * StrUtil.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
     * StrUtil.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
     * StrUtil.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
     * StrUtil.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
     * StrUtil.indexOfIgnoreCase("abc", "", 9)        = -1
     * </pre>
     *
     * @param str       字符串
     * @param searchStr 需要查找位置的字符串
     * @param fromIndex 起始位置
     * @return 位置
     * @since 3.2.1
     */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int fromIndex) {
        return indexOf(str, searchStr, fromIndex, true);
    }

    /**
     * 指定范围内反向查找字符串
     *
     * @param str        字符串
     * @param searchStr  需要查找位置的字符串
     * @param fromIndex  起始位置
     * @param ignoreCase 是否忽略大小写
     * @return 位置
     * @since 3.2.1
     */
    public static int indexOf(final CharSequence str, CharSequence searchStr, int fromIndex, boolean ignoreCase) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }

        final int endLimit = str.length() - searchStr.length() + 1;
        if (fromIndex > endLimit) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return fromIndex;
        }

        if (!ignoreCase) {
            // 不忽略大小写调用JDK方法
            return str.toString().indexOf(searchStr.toString(), fromIndex);
        }

        for (int i = fromIndex; i < endLimit; i++) {
            if (isSubEquals(str, i, searchStr, 0, searchStr.length(), true)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 截取两个字符串的不同部分（长度一致），判断截取的子串是否相同<br>
     * 任意一个字符串为null返回false
     *
     * @param str1       第一个字符串
     * @param start1     第一个字符串开始的位置
     * @param str2       第二个字符串
     * @param start2     第二个字符串开始的位置
     * @param length     截取长度
     * @param ignoreCase 是否忽略大小写
     * @return 子串是否相同
     * @since 3.2.1
     */
    public static boolean isSubEquals(CharSequence str1, int start1, CharSequence str2, int start2, int length, boolean ignoreCase) {
        if (null == str1 || null == str2) {
            return false;
        }

        return str1.toString().regionMatches(ignoreCase, start1, str2.toString(), start2, length);
    }

    /**
     * 重复字符串
     *
     * @param string 字符串
     * @param times  次数
     * @return 字符串
     */
    public static String repeat(String string, int times) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < times; i++) {
            sb.append(string);
        }
        return sb.toString();
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
}
