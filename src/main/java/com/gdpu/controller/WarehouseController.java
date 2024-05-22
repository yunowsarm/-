package com.gdpu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdpu.bean.Manager;
import com.gdpu.bean.Warehouse;
import com.gdpu.common.DataGridView;
import com.gdpu.common.WebUtils;
import com.gdpu.service.WarehouseService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Resource
    WarehouseService warehouseService;
    //提供下拉框
    @RequestMapping("loadAllHouseForSelect")
    public DataGridView loadAllHouseForSelect(){
        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<Warehouse>();
        Manager manager = (Manager) WebUtils.getSession().getAttribute("user");
        queryWrapper.eq(0!= manager.getRoleId(),"house_id", manager.getRoleId());
        List<Warehouse> list = warehouseService.list(queryWrapper);
        return new DataGridView(list);
    }

}

