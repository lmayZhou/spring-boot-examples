package com.lmaye.spring.boot.api2doc.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * -- 通用的HTTP接口返回对象
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应代码
     */
    @ApiComment(value = "响应代码", sample = "200")
    private Integer code;

    /**
     * 响应消息
     */
    @ApiComment(value = "响应消息", sample = "200")
    private String msg;

    /**
     * 响应数据
     */
    @ApiComment(value = "响应数据", sample = "T")
    private T data;

    /**
     * 操作成功
     *
     * @param data 数据
     * @param <T>  泛型
     * @return Response<T>
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "操作成功", data);
    }

    /**
     * 操作成功
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  泛型
     * @return Response<T>
     */
    public static <T> Response<T> success(String msg, T data) {
        return new Response<>(200, msg, data);
    }

    /**
     * 操作失败
     *
     * @param <T> 泛型
     * @return Response<T>
     */
    public static <T> Response<T> failed() {
        ErrorInfo error = ErrorInfo.error();
        return new Response<>(error.getCode(), error.getMsg(), null);
    }

    /**
     * 操作失败
     * - 自定义信息
     *
     * @param msg 错误信息
     * @param <T> 泛型
     * @return Response<T>
     */
    public static <T> Response<T> failed(String msg) {
        ErrorInfo error = ErrorInfo.error(msg);
        return new Response<>(error.getCode(), error.getMsg(), null);
    }

    /**
     * 操作失败
     * - 错误对象
     *
     * @param error 错误对象
     * @param <T>   泛型
     * @return Response<T>
     */
    public static <T> Response<T> failed(ErrorInfo error) {
        return new Response<>(error.getCode(), error.getMsg(), null);
    }

    /**
     * 响应结果
     *
     * @param code 代码
     * @param msg  消息
     * @param data 数据
     * @param <T>  泛型
     * @return Response<T>
     */
    public static <T> Response<T> response(int code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    /**
     * 判断响应结果是否成功
     *
     * @return boolean
     */
    public boolean isSuccess() {
        if (Objects.isNull(code)) {
            return false;
        }
        return Objects.equals(Integer.valueOf("200"), code);
    }
}
