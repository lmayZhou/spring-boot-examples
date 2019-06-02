package com.lmaye.examples.common.common;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * -- 错误对象
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Getter
@Builder
public class ErrorInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 获取基本错误
     *
     * @return ErrorInfo
     */
    public static ErrorInfo error() {
        return ErrorInfo.builder().code(-100).msg("处理失败").build();
    }

    /**
     * 获取基本错误
     *
     * @param msg 错误信息
     * @return ErrorInfo
     */
    public static ErrorInfo error(String msg) {
        return ErrorInfo.builder().code(-100).msg(msg).build();
    }

    /**
     * 获取未授权错误
     *
     * @return ErrorInfo
     */
    public static ErrorInfo unauthorizedErrorInfo() {
        return ErrorInfo.builder().code(401).msg("请求未授权").build();
    }

    /**
     * 获取权限不足错误
     *
     * @return ErrorInfo
     */
    public static ErrorInfo forbiddenErrorInfo(String msg) {
        return ErrorInfo.builder().code(403).msg(msg).build();
    }

    /**
     * 获取地址不存在错误
     *
     * @return ErrorInfo
     */
    public static ErrorInfo notFoundErrorInfo() {
        return ErrorInfo.builder().code(404).msg("请求地址不存在").build();
    }

    /**
     * 获取服务错误
     *
     * @return ErrorInfo
     */
    public static ErrorInfo serviceErrorInfo() {
        return ErrorInfo.builder().code(500).msg("服务器开小差了").build();
    }
}
