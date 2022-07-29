package com.demo.dao.po;


public class CarInvDetailPO {

  private long id;
  private String carModelId;
  private String dailyDate;
  private long totalQuantity;

  /**
   * the combinekey consists of carModelId and dailyDate
   */
  private String combineKey;

  public String getCombineKey() {
    return combineKey;
  }

  public void setCombineKey(String combineKey) {
    this.combineKey = combineKey;
  }

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

  public String getDailyDate() {
    return dailyDate;
  }

  public void setDailyDate(String dailyDate) {
    this.dailyDate = dailyDate;
  }

  public long getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(long totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

}
