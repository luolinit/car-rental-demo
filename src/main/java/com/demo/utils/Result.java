package com.demo.utils;



import java.io.Serializable;

/**
 * @author luolin
 * @version V1.0
 * @Title: Result.java
 * @Package com.demo.utils
 * @Description
 * @date 2022 07-27 15:12.
 */
public class Result implements Serializable {
    private String responseCode;
    private String responseMsg;
    private Object data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
