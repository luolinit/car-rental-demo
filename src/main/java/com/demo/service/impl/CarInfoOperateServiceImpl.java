
package com.demo.service.impl;

import com.demo.common.ErrorCodeEnum;
import com.demo.dao.CarInfoMapper;
import com.demo.dao.CarInvDetailMapper;
import com.demo.dao.CarOrderMapper;
import com.demo.dao.po.CarInvDetailPO;
import com.demo.dao.po.CarInvOperatePO;
import com.demo.dao.po.CarModelInfoPO;
import com.demo.dao.po.CarOrderPO;
import com.demo.domain.CarBookingDO;
import com.demo.service.CarInfoOperateService;
import com.demo.utils.Result;
import com.demo.utils.ResultUtil;
import com.demo.utils.SimpleOrderNoGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.demo.common.ErrorCodeEnum.DATE_NOT_AVAILABILITY;

/**
 * @author luolin
 * @version V1.0
 * @Title: CarInfoOperateServiceImpl.java
 * @Package com.demo.service.impl
 * @Description
 * @date 2022 07-28 15:12.
 */
@Service("carInfoOperateService")
public class CarInfoOperateServiceImpl implements CarInfoOperateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarInfoOperateServiceImpl.class);

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Autowired
    private CarInvDetailMapper carInvDetailMapper;

    @Autowired
    private CarOrderMapper carOrderMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public Result getCarModelsInfo() {
        try {
            List<CarModelInfoPO> demoCarInfoPOS= carInfoMapper.queryAllCarModelInfo();
            return ResultUtil.success(demoCarInfoPOS);
        } catch (Exception e) {
            LOGGER.error("getCarModelsInfo exception:",e);
            return  ResultUtil.fail(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public Result checkCarAvailability(CarBookingDO carBookingDO) {
        try {
            Result checkResult=checkParam(carBookingDO);
            if(!ResultUtil.isSuccess(checkResult)){
                return checkResult;
            }
            List<String> combineKeys= assemblyInvQueryCombineKeys(carBookingDO);
            List<CarInvDetailPO> carInvDetailPOS=carInvDetailMapper.queryCarInvListByCombineKey(combineKeys);
            if(CollectionUtils.isEmpty(carInvDetailPOS)){
                return ResultUtil.fail(DATE_NOT_AVAILABILITY);
            }
            for(CarInvDetailPO carInvDetailPO:carInvDetailPOS){
                if(carInvDetailPO.getTotalQuantity()<carBookingDO.getBookingQty()){
                    return ResultUtil.fail(DATE_NOT_AVAILABILITY);
                }
            }

            return ResultUtil.success();
        } catch (Exception e) {
            LOGGER.error("checkCarAvailability exception:",e);
            return ResultUtil.fail(ErrorCodeEnum.SYSTEM_EXCEPTION);
        }

    }

    @Override
    public Result carBooking(CarBookingDO carBookingDO) {
        Result carDateAvailableResult=checkCarAvailability(carBookingDO);
        if(!ResultUtil.isSuccess(carDateAvailableResult)){
            return carDateAvailableResult;
        }
        //String allocateCarNo=allocateCarNo();

        //assemble rental info
        List< CarInvOperatePO > carInvOperatePOs = assembleCarInvOperateInfo(carBookingDO);
        CarOrderPO carOrderPO = assembleCarOrderInfo(carBookingDO,"china-223456");

        //update the car model inventory and store order info
        return transactionTemplate.execute(new TransactionCallback<Result>() {
            @Override
            public Result doInTransaction(TransactionStatus transactionStatus) {
                try {
                    int recordsUpdated=carInvDetailMapper.occupyCarInv(carInvOperatePOs);
                    if(recordsUpdated!=carInvOperatePOs.size()){
                        transactionStatus.setRollbackOnly();
                        return ResultUtil.fail(ErrorCodeEnum.DATE_NOT_AVAILABILITY);
                    }
                    carOrderMapper.createCarBookingOrder(carOrderPO);
                } catch (Exception e) {
                    LOGGER.error("carBooking exception:",e);
                    transactionStatus.setRollbackOnly();
                    return ResultUtil.fail(ErrorCodeEnum.SYSTEM_EXCEPTION);
                }
                return ResultUtil.success(carOrderPO.getOrderNo());
            }
        });
    }

    @Override
    public Result cancelCarBooking(String orderNo) {
        //TODO
        return null;
    }


    private CarOrderPO assembleCarOrderInfo(CarBookingDO carBookingDO,String allocateCarNo) {
        CarOrderPO carOrderPO=new CarOrderPO();
        carOrderPO.setOrderNo(SimpleOrderNoGenerate.generateOrderNo());
        carOrderPO.setCarNo(allocateCarNo);
        carOrderPO.setContactPhone(carBookingDO.getContactPhone());
        carOrderPO.setCustomerName(carBookingDO.getCustomerName());
        carOrderPO.setPickUpTime(carBookingDO.getPickUpTime());
        carOrderPO.setReturnTime(carBookingDO.getReturnTime());
        return carOrderPO;
    }

    private List<CarInvOperatePO> assembleCarInvOperateInfo(CarBookingDO carBookingDO) {
        List<CarInvOperatePO> carInvOperatePOS=new ArrayList<>();

        List<String> invQueryCombineKeys=assemblyInvQueryCombineKeys(carBookingDO);

        for(String combineKey:invQueryCombineKeys){
            CarInvOperatePO carInvOperatePO=new CarInvOperatePO();
            carInvOperatePO.setCombineKey(combineKey);
            carInvOperatePO.setOccupiedQty(carBookingDO.getBookingQty());

            carInvOperatePOS.add(carInvOperatePO);
        }
        return carInvOperatePOS;

    }

    private List<String> assemblyInvQueryCombineKeys(CarBookingDO carBookingDO){
        List<String> dates=getBetweenDate(carBookingDO.getPickUpTime(), carBookingDO.getReturnTime());
        List<String> combineKeys=new ArrayList<>();
        for(String date:dates){
            String combineKey= carBookingDO.getCarModel()+date;
            combineKeys.add(combineKey);
        }
        return combineKeys;
    }

    public List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<>();
        try {

            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);


            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {

                list.add(sdf.format(startDate));

                calendar.setTime(startDate);

                calendar.add(Calendar.DATE, 1);

                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            LOGGER.error("parse exception:",e);
        }
        return list;
    }

    private Result checkParam(CarBookingDO carBookingDO){
        // should check the model from the cache,temp process it.
        List<String> carModelType= Arrays.asList("toyota_carmy_01","bmw_650");
        if(!carModelType.contains(carBookingDO.getCarModel())){
            return  ResultUtil.fail(ErrorCodeEnum.PARAM_INVALID);
        }
        return ResultUtil.success();
    }
}
