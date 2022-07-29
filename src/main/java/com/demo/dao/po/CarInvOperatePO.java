
package com.demo.dao.po;

import java.util.Date;

/**
 * @author luolin
 * @version V1.0
 * @Title: CarInvOperatePO.java
 * @Package com.demo.dao.po
 * @Description
 * @date 2022 07-28 18:27.
 */
public class CarInvOperatePO {
    private Date currentDate;

    private String carModelId;

    //the combinekey consists of carModelId and currentDate
    private String combineKey;

    private long occupiedQty;

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    public String getCombineKey() {
        return combineKey;
    }

    public void setCombineKey(String combineKey) {
        this.combineKey = combineKey;
    }

    public long getOccupiedQty() {
        return occupiedQty;
    }

    public void setOccupiedQty(long occupiedQty) {
        this.occupiedQty = occupiedQty;
    }
}
