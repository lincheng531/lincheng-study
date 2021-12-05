package com.lincheng.study.exception;

import com.lincheng.study.enums.BaseError;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-12-05 22:14
 **/
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = -4411397433336114092L;

    // 错误码
    protected String errorCode;
    // 错误信息
    protected String errorMsg;

    public BusinessException() {
        super();
    }

    public BusinessException(BaseError errorInfoInterface) {
        super(errorInfoInterface.getResultCode());
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public BusinessException(BaseError errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
