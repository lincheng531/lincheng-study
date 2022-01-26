package com.lincheng.study.exception;

import com.lincheng.study.enums.BaseErrorEnum;
import com.lincheng.study.utils.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-05 22:20
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(BusinessException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return ResultBody.error(BaseErrorEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(Exception e) {
        log.error("未知异常！原因是:", e);
        return ResultBody.error(BaseErrorEnum.INTERNAL_SERVER_ERROR);
    }


    //运行时异常
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResultBody runtimeExceptionHandler(RuntimeException ex) {
        return ResultBody.error(BaseErrorEnum.INTERNAL_SERVER_ERROR);
    }


    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ResultBody classCastExceptionHandler(ClassCastException ex) {
        return ResultBody.error(BaseErrorEnum.INTERNAL_SERVER_ERROR);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public ResultBody iOExceptionHandler(IOException ex) {
        return ResultBody.error(BaseErrorEnum.INTERNAL_SERVER_ERROR);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public ResultBody noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return ResultBody.error(BaseErrorEnum.INTERNAL_SERVER_ERROR);
    }

}
