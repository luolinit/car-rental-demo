
package com.demo.domain;

import java.util.Date;

/**
 * @author luolin
 * @version V1.0
 * @Title: CarModelInfoDO.java
 * @Package com.demo.domain
 * @Description
 * @date 2022 07-28 15:10.
 */
public class CarModelInfoDO {
    private long id;
    private String carModelId;
    private String carModelName;
    private double dailyRentPrice;
    private String storeAddress;
    private String storeName;
    private String storeWorkTime;
    private String creatorId;
    private Date createTime;
    private String modifierId;
    private Date modifyTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }


    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }


    public double getDailyRentPrice() {
        return dailyRentPrice;
    }

    public void setDailyRentPrice(double dailyRentPrice) {
        this.dailyRentPrice = dailyRentPrice;
    }


    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    public String getStoreWorkTime() {
        return storeWorkTime;
    }

    public void setStoreWorkTime(String storeWorkTime) {
        this.storeWorkTime = storeWorkTime;
    }


    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }


    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
