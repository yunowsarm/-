package com.gdpu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdpu.bean.Warehouse;
import org.apache.ibatis.annotations.Select;

public interface WarehouseMapper extends BaseMapper<Warehouse> {
    @Select("SELECT MAX(house_id) FROM warehouse")
    Integer getMaximumId();
}
