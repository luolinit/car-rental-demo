package com.demo.controller;

import com.demo.domain.CarBookingDO;
import com.demo.service.CarInfoOperateService;
import com.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author luolin
 * @version V1.0
 * @Title: TestController.java
 * @Package com.demo.controller
 * @Description
 * @date 2022 07-26 16:20.
 */
@Controller
@Api(value="booking car",description="This API enables its users to get our company cars related information and send bookings to it. ")
public class BookingCarController {
    @Resource
    private CarInfoOperateService carInfoOperateService;

    @RequestMapping("/allCarModels")
    @ResponseBody
    @ApiOperation(value="CARS GET",httpMethod="GET",notes="This call will return lists of car models")
    public Result getCars(HttpServletRequest request){
        return carInfoOperateService.getCarModelsInfo();
    }


    @RequestMapping("/car/availability")
    @ResponseBody
    @ApiOperation(value="CHECK CAR AVAILABILITY",httpMethod="POST",notes="This method is used to check the number of car available for a particular booking date")
    public Result carAvailability(@RequestBody @ApiParam(name="the object of car availability",value="input the json format",required=true) CarBookingDO carBookingDO){
        return carInfoOperateService.checkCarAvailability(carBookingDO);
    }

    @RequestMapping("/car/booking")
    @ResponseBody
    @ApiOperation(value="CAR BOOKING",httpMethod="POST",notes="This method is used to block the inventory and Initialise order once the user A has provided the booking details")
    public Result carBooking(@RequestBody @ApiParam(name="the object of car booking",value="input the json format",required=true) CarBookingDO carBookingDO){
        return carInfoOperateService.carBooking(carBookingDO);
    }

    @RequestMapping("/car/cancelBooking")
    @ResponseBody
    @ApiOperation(value="CANCEL CAR BOOKING",httpMethod="POST",notes="This method is used to cancel the order and rollback the inventory")
    public Result cancelcarBooking(@RequestBody @ApiParam(name="the order no",value="input the json format",required=true) String orderNo){
        return carInfoOperateService.cancelCarBooking(orderNo);
    }
}
