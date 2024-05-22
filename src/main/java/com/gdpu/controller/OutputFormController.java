package com.gdpu.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdpu.bean.*;
import com.gdpu.common.DataGridView;
import com.gdpu.common.ResultObj;
import com.gdpu.common.WebUtils;
import com.gdpu.service.*;
import com.gdpu.vo.OutputFormVo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/output")
public class OutputFormController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private CustomerService customerService;

    @Resource
    private OutputFormService outputFormService;

    @Resource
    private StockService stockService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private ManagerService managerService;

    @RequestMapping("loadAllOutput")
    public DataGridView loadAllOutput(OutputFormVo outputFormVo) {
        IPage<OutputForm> page = new Page<>(outputFormVo.getPage(),outputFormVo.getLimit());
        QueryWrapper<OutputForm> queryWrapper = new QueryWrapper();
        queryWrapper.eq(null!=outputFormVo.getFormId(),"form_id",outputFormVo.getFormId());
        queryWrapper.eq(null != outputFormVo.getCustomerId() && outputFormVo.getCustomerId()!=0,"customer_id",outputFormVo.getCustomerId());
        Manager user = (Manager) WebUtils.getSession().getAttribute("user");
        queryWrapper.eq(null!=user.getRoleId() && 0!=user.getRoleId(),"house_id",user.getRoleId());
        outputFormService.page(page,queryWrapper);
        List<OutputForm> records = page.getRecords();
        for(OutputForm outputForm : records){
            Customer customer = customerService.getById(outputForm.getCustomerId());
            if (null != customer){
                outputForm.setCustomerName(customer.getName());
                outputForm.setAddress(customer.getAddress());
            }
            Goods goods = goodsService.getById(outputForm.getGoodsId());
            if(null != goods){
                outputForm.setGoodsName(goods.getName());
                outputForm.setSize(goods.getSize());
                outputForm.setPackages(goods.getPackages());
                outputForm.setPrice(goods.getPrice());
            }
            Manager manager = managerService.getById(outputForm.getUserId());
            if(null != manager){
                outputForm.setUserName(manager.getName());
            }

            Warehouse warehouse = warehouseService.getById(outputForm.getHouseId());
            if(null != warehouse){
                outputForm.setHouseName(warehouse.getName());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("addOutput")
    public ResultObj addOutput(OutputFormVo outputFormVo){
        try {
            Manager manager = (Manager) WebUtils.getSession().getAttribute("user");
            outputFormVo.setCreateTime(new Date());
            outputFormVo.setUserId(manager.getUserId());
            QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("goods_id",outputFormVo.getGoodsId());
            queryWrapper.eq("house_id",outputFormVo.getHouseId());
            Stock stock = stockService.getOne(queryWrapper);
            UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("goods_id",stock.getGoodsId());
            updateWrapper.eq("house_id",stock.getHouseId());
            System.out.println("stock = " + stock);
            if(stock.getNumber()-outputFormVo.getChangeNumber() < stock.getUnderAlert()){
                return ResultObj.ADD_ERROR_UNDER;
            } else if(outputFormVo.getChangeNumber() ==0){
                return ResultObj.ERROR_ZERO;
            } else {
                stock.setNumber(stock.getNumber()-outputFormVo.getChangeNumber());
            }
            outputFormService.save(outputFormVo);
            stockService.update(stock,updateWrapper);
            return ResultObj.ADD_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("deleteOutput")
    public ResultObj deleteInput(Integer id){
        try {
            outputFormService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

