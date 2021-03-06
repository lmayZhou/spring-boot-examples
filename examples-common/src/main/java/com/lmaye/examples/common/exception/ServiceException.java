package com.lmaye.examples.common.exception;

import com.lmaye.examples.common.common.ErrorInfo;

/**
 * -- 业务异常
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误信息
     */
    private ErrorInfo error;

    public ServiceException(ErrorInfo error) {
        super(error.getMsg());
        this.error = error;
    }

    public ServiceException(ErrorInfo error, Throwable cause) {
        super(error.getMsg(), cause);
        this.error = error;
    }

    /**
     * 获取错误信息
     *
     * @return ErrorInfo
     */
    public ErrorInfo getError() {
        return error;
    }
}
