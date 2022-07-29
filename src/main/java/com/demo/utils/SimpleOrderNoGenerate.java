/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luolin
 * @version V1.0
 * @Title: SimpleOrderNoGenerate.java
 * @Package com.demo.utils
 * @Description
 * @date 2022 07-29 1:08.
 */
public class SimpleOrderNoGenerate {
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHH");

    /**
     *  generate order no in single computer environment
     * @return
     */
    public static String generateOrderNo(){
        LocalDateTime dataTime = LocalDateTime.now();
        if(SEQ.intValue() > 9000){
            SEQ.getAndSet(1000);
        }
        return  dataTime.format(DF_FMT_PREFIX)+ SEQ.getAndIncrement();
    }
}
