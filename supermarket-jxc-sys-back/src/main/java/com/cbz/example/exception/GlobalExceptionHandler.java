package com.cbz.example.exception;

import com.cbz.example.common.Result;
import com.cbz.example.config.CustomConfig;
import com.cbz.example.exception.enums.SystemExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.SignatureException;
import java.util.*;

/**
 * @author 11272
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private CustomConfig customConfig;

    /**
     * 处理请求方法异常 方法不允许
     *
     * @param e e
     * @return r
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return Result.fail(SystemExceptionEnum.METHOD_NOT_ALLOWED.getCode(), e.getMessage());
    }

    /**
     * 参数异常
     *
     * @param e e
     * @return r
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Map<String, String>> handleValidationException(ConstraintViolationException e) {
        e.printStackTrace();

        Map<String, String> errors = new LinkedHashMap<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        if (CustomConfig.TO_DATA.equals(customConfig.getParamsCheckResult())) {
            return Result.fail(SystemExceptionEnum.REQUEST_PARAMS_CHECK_FAIL.getCode(),
                    SystemExceptionEnum.REQUEST_PARAMS_CHECK_FAIL.getMessage(),
                    errors);
        } else {
            Optional<Map.Entry<String, String>> first = errors.entrySet().stream().findFirst();
            String message = first.map(stringStringEntry -> stringStringEntry.getKey() + ":" + stringStringEntry.getValue()).orElseGet(SystemExceptionEnum.REQUEST_PARAMS_CHECK_FAIL::getMessage);
            return Result.fail(SystemExceptionEnum.REQUEST_PARAMS_CHECK_FAIL.getCode(), message, null);
        }
    }

    /**
     * 处理绑定异常
     *
     * @param e e
     * @return r
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Map<String, String>> handleBindException(BindException e) {
        e.printStackTrace();
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        if (CustomConfig.TO_DATA.equals(customConfig.getParamsCheckResult())) {
            return Result.fail(SystemExceptionEnum.REQUEST_PARAMS_BIND_FAIL.getCode(),
                    SystemExceptionEnum.REQUEST_PARAMS_BIND_FAIL.getMessage(),
                    errors);
        }else {
            Optional<Map.Entry<String, String>> first = errors.entrySet().stream().findFirst();
            String message = first.map(stringStringEntry -> stringStringEntry.getKey() + ":" + stringStringEntry.getValue()).orElseGet(SystemExceptionEnum.REQUEST_PARAMS_CHECK_FAIL::getMessage);
            return Result.fail(SystemExceptionEnum.REQUEST_PARAMS_CHECK_FAIL.getCode(), message, null);
        }
    }

    /**
     * 处理token格式问题
     *
     * @param e e
     * @return r
     */
    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleSignatureException(SignatureException e) {
        e.printStackTrace();
        return Result.fail(SystemExceptionEnum.TOKEN_NOT_VALID.getCode(), e.getMessage());
    }

    /**
     * 处理业务异常
     *
     * @param e e
     * @return r
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleBusinessException(BusinessException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理系统异常
     *
     * @param e e
     * @return r
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Object> handleException(Exception e) {
        e.printStackTrace();
        return Result.error(SystemExceptionEnum.INTERNAL_SERVER_ERROR.getMessage());
    }
}
