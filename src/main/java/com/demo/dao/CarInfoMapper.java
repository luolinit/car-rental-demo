package com.demo.dao;


import com.demo.dao.po.CarInfoPO;
import com.demo.dao.po.CarModelInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarInfoMapper {
    List<CarModelInfoPO> queryAllCarModelInfo();

    List<CarInfoPO>  queryCarInfosByModel();
}
