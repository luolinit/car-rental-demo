package com.demo.utils;

import com.demo.common.ErrorCodeEnum;

/**
 * @author luolin
 * @version V1.0
 * @Title: ResultUtil.java
 * @Package com.demo.utils
 * @Description
 * @date 2022 07-27 16:12.
 */
public class ResultUtil {
    public static final String SUCCESS = "1000";
    public static final String SUCCESS_MSG = "SUCCESS";

    public static Result success(Object... data){
        Result rt = new Result();
        rt.setResponseCode(SUCCESS);
        rt.setResponseMsg(SUCCESS_MSG);
        if(data.length>0){
            rt.setData(data);
        }
        return rt;
    }

    public static Result fail(ErrorCodeEnum errorCodeEnum){
        Result rt = new Result();
        rt.setResponseCode(errorCodeEnum.errorCode);
        rt.setResponseMsg(errorCodeEnum.errorMsg);
        return rt;
    }

    public static Result fail(String code,String msg){
        Result rt = new Result();
        rt.setResponseCode(code);
        rt.setResponseMsg(msg);
        return rt;
    }

    public static boolean isSuccess(Result result){
        return SUCCESS.equals(result.getResponseCode());
    }
}
