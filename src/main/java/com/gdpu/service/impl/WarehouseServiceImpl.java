package com.gdpu.service.impl;

import com.gdpu.bean.Warehouse;
import com.gdpu.mapper.WarehouseMapper;
import com.gdpu.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-06-29
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public Integer getMaximumId() {
        Integer maxId = warehouseMapper.getMaximumId();
        return maxId != null ? maxId : 0;
    }
}
