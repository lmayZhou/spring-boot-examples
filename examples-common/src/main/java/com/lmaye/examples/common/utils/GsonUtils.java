package com.lmaye.examples.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * -- Json 转换工具
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public abstract class GsonUtils {
    /**
     * 把Json字符串转成JAVA对象
     *
     * @param json  Json字符串
     * @param clazz Java对象
     * @return T
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").create();
        return gson.fromJson(json, clazz);
    }

    /**
     * 把Java对象转成字符串
     *
     * @param object 对象
     * @return String
     */
    public static String toJson(Object object) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").create();
        return gson.toJson(object);
    }
}
