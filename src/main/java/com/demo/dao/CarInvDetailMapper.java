package com.demo.dao;


import com.demo.dao.po.CarInvDetailPO;
import com.demo.dao.po.CarInvOperatePO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarInvDetailMapper {
    /**
     * query car daily inv list
     * @param combineKeys the combinekeys consists of carModelId and currentDate
     * @return Daily inventory of car model
     */
    List<CarInvDetailPO> queryCarInvListByCombineKey(List<String> combineKeys);

    /**
     * update the car inv
     * @param carInvOperatePOs cars status update info
     * @return the record of updated
     */
    int occupyCarInv(List<CarInvOperatePO> carInvOperatePOs);

    void batchInsertInv(List<CarInvDetailPO> carInvDetailPOS);
}
