package com.lmaye.examples.common.exception;

import com.lmaye.examples.common.common.ErrorInfo;

/**
 * 验证异常
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public class ValidateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误
     */
    private ErrorInfo error;

    public ValidateException() {
        super(ErrorInfo.error().getMsg());
        this.error = ErrorInfo.error();
    }

    public ValidateException(ErrorInfo error) {
        super(error.getMsg());
        this.error = error;
    }

    public ValidateException(ErrorInfo error, Throwable cause) {
        super(error.getMsg(), cause);
        this.error = error;
    }

    /**
     * 获取错误
     *
     * @return 错误
     */
    public ErrorInfo getError() {
        return error;
    }
}
