
package com.demo.common;

/**
 * @author luolin
 * @version V1.0
 * @Title: ErrorCodeEnum.java
 * @Package com.demo
 * @Description
 * @date 2022 07-28 15:41.
 */
public enum ErrorCodeEnum {
    SYSTEM_EXCEPTION("1100","System Exception,pls contact with the Provider."),
    PARAM_INVALID("1200","Invalid field name in the parameter"),
    PARAM_MISSING("1201","Required field missing"),
    DATE_NOT_AVAILABILITY("1300","Insufficient Inventory. Please try some other Date"),
    INVALID_TOKEN("1400","Invalid or Expired Token")
    ;

    public final String errorCode;
    public final String errorMsg;

    ErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}