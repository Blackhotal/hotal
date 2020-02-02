package com.zxy.exception;

import com.zxy.util.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 统一异常处理
 * @author black
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return ResponseEntity.error(500, e.getMessage());
    }
}
