package com.lincheng.study.domain.base;

import java.io.Serializable;

/**
 * @author lincheng5
 * @date 2021/12/28 21:23
 */
public class Response<T> implements Serializable {

    /**
     * 状态码
     */
    private String resultCode;

    /**
     * 结果描述
     */
    private String resultMsg;

    /**
     * 业务数据
     */
    private T result = null;

    public Response() {
        super();
    }

    public Response(String resultCode, String resultMsg) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public Response(String resultCode, String resultMsg, T result) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }
}
