package com.demo.common;

public class BusinessException extends RuntimeException{

        private String code;
        private Object data;

        public BusinessException(Throwable e) {
            super(e);
        }

        public BusinessException(String code, String msg) {
            super(msg);
            this.code = code;
        }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.errorMsg);
        this.code = errorCodeEnum.errorCode;
    }

        public String getCode() {
            return this.code;
        }

        public Object getData() {
            return this.data;
        }


}