package com.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel()
public class CarBookingDO implements Serializable {

    @ApiModelProperty(value = "the start time of rental",required = true,example = "2022-07-01")
    private String pickUpTime;

    @ApiModelProperty(value = "nd time of rental",required = true,example = "2022-07-03")
    private String returnTime;

    /**
     * id of car model
     */
    @ApiModelProperty(value = "id of car model",required = true,example = "toyota_carmy_01")
    private String carModel;

    @ApiModelProperty(value = "the num of car model",required = true,example = "2")
    private Long bookingQty;

    @ApiModelProperty(value = "cutomer name",required = false,example = "customer A")
    private String customerName;

    @ApiModelProperty(value = "contact phone",required = false,example = "5454554545")
    private String contactPhone;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Long getBookingQty() {
        return bookingQty;
    }

    public void setBookingQty(Long bookingQty) {
        this.bookingQty = bookingQty;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
