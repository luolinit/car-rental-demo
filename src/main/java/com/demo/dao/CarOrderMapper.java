package com.demo.dao;


import com.demo.dao.po.CarOrderPO;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOrderMapper {
    void createCarBookingOrder(CarOrderPO carOrderPO);
}
