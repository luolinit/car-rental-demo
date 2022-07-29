package com.demo.service;

import com.demo.domain.CarBookingDO;
import com.demo.utils.Result;

/**
 * @author luolin
 * @version V1.0
 * @Title: CarInfoOperateService.java
 * @Package com.demo.service
 * @Description
 * @date 2022 07-28 14:56.
 */
public interface CarInfoOperateService {
    Result getCarModelsInfo();

    Result checkCarAvailability(CarBookingDO carBookingDO);

    Result carBooking(CarBookingDO carBookingDO);

    Result cancelCarBooking(String orderNo);

    Result initCarInv(CarBookingDO carBookingDO);

}
