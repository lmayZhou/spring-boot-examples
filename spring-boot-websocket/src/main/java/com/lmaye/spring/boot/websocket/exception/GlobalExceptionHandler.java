package com.lmaye.spring.boot.websocket.exception;

import com.lmaye.examples.common.common.Response;
import com.lmaye.examples.common.exception.CommonException;
import com.lmaye.examples.common.exception.HandleException;
import com.lmaye.examples.common.exception.ServiceException;
import com.lmaye.examples.common.utils.GsonUtils;
import com.lmaye.examples.common.validator.ValidateErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * -- 全局异常处理
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 所有异常处理
     * - 不可知的
     *
     * @param e 所有异常
     * @return Response
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.failed(e.getMessage());
    }

    /**
     * 基础异常处理
     *
     * @param e 基础异常
     * @return Response
     */
    @ExceptionHandler(CommonException.class)
    public Response handleCommonException(CommonException e) {
        log.error(e.getMessage(), e);
        return Response.failed(e.getError());
    }

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return Response
     */
    @ExceptionHandler(ServiceException.class)
    public Response handleServiceException(ServiceException e) {
        log.error(e.getMessage(), e);
        return Response.failed(e.getError());
    }

    /**
     * 处理异常处理
     *
     * @param e 处理异常
     * @return Response
     */
    @ExceptionHandler(HandleException.class)
    public Response handleHandleException(HandleException e) {
        log.error(e.getMessage(), e);
        return Response.failed(e.getError());
    }

    /**
     * 对象参数校验异常处理
     *
     * @param e 对象参数校验异常
     * @return Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Response.failed(GsonUtils.toJson(ValidateErrors.builder().errors(e.getBindingResult()).build()));
    }

    /**
     * 方法参数校验异常处理
     *
     * @param e 方法参数校验异常
     * @return Response
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Map<String, String> fieldErrors = new HashMap<>(constraintViolations.size());
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            Path propertyPath = constraintViolation.getPropertyPath();
            Iterator<Path.Node> iterator = propertyPath.iterator();
            Path.Node node = null;
            while (iterator.hasNext()) {
                node = iterator.next();
            }
            if (node != null) {
                fieldErrors.put(node.getName(), constraintViolation.getMessage());
            }
        }
        return Response.failed(GsonUtils.toJson(ValidateErrors.builder().fieldErrors(fieldErrors).build()));
    }
}
