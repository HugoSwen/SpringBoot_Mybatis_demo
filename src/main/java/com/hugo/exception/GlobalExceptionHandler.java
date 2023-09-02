package com.hugo.exception;

import com.hugo.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.error("操作异常");
    }

}
