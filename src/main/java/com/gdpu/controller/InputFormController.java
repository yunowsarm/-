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
import com.gdpu.vo.InputFormVo;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/input")
public class InputFormController {
    @Resource
    private GoodsService goodsService;

    @Resource
    private ProviderService providerService;

    @Resource
    private InputFormService inputFormService;

    @Resource
    private StockService stockService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private ManagerService managerService;

    @RequestMapping("loadAllInput")
    public DataGridView loadAllInput(InputFormVo inputFormVo) {
        IPage<InputForm> page = new Page<>(inputFormVo.getPage(), inputFormVo.getLimit());
        QueryWrapper<InputForm> queryWrapper = new QueryWrapper();
        queryWrapper.eq(null!=inputFormVo.getFormId(),"form_id",inputFormVo.getFormId());
        queryWrapper.eq(null != inputFormVo.getProviderId() && inputFormVo.getProviderId()!=0,"provider_id",inputFormVo.getProviderId());
        Manager user = (Manager) WebUtils.getSession().getAttribute("user");
        queryWrapper.eq(0!=user.getRoleId(),"house_id",user.getRoleId());
        inputFormService.page(page,queryWrapper);
        List<InputForm> records = page.getRecords();
        for(InputForm inputForm : records){
            Provider provider = providerService.getById(inputForm.getProviderId());
            if (null != provider){
                inputForm.setProviderName(provider.getName());
                inputForm.setAddress(provider.getAddress());
            }
            Goods goods = goodsService.getById(inputForm.getGoodsId());
            if(null != goods){
                inputForm.setGoodsName(goods.getName());
                inputForm.setSize(goods.getSize());
                inputForm.setPackages(goods.getPackages());
                inputForm.setPrice(goods.getPrice());
            }
            Manager manager = managerService.getById(inputForm.getUserId());
            if(null != manager){
                inputForm.setUserName(manager.getName());
            }

            Warehouse warehouse = warehouseService.getById(inputForm.getHouseId());
            if(null != warehouse){
                inputForm.setHouseName(warehouse.getName());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    @RequestMapping("addInput")
    public ResultObj addInput(InputFormVo inputFormVo){
        try {
            Manager manager = (Manager) WebUtils.getSession().getAttribute("user");
            inputFormVo.setCreateTime(new Date());
            inputFormVo.setUserId(manager.getUserId());
            Integer goodsId = inputFormVo.getGoodsId();
            Integer houseId = inputFormVo.getHouseId();
            Integer changeNumber = inputFormVo.getChangeNumber();
            QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("goods_id",goodsId);
            queryWrapper.eq("house_id",houseId);
            Stock stock = stockService.getOne(queryWrapper);
            UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();
            if(null != stock){              //如果库存中有这件商品
                updateWrapper.eq("goods_id",stock.getGoodsId());
                updateWrapper.eq("house_id",stock.getHouseId());
            }else {                         //如果库存中没有这件商品
                QueryWrapper<Stock> query = new QueryWrapper();
                query.eq("house_id",houseId);
    //                query.eq("goods_id",0);
                Integer upperAlert = stockService.getOne(query).getUpperAlert();
                if(changeNumber < upperAlert){
                    Stock stock1 = new Stock(goodsId,houseId,changeNumber,upperAlert,0);
                    System.out.println("stock1 = " + stock1);
                    stockService.save(stock1);
                    inputFormService.save(inputFormVo);
                    return ResultObj.ADD_SUCCESS;
                }

            }
            if(changeNumber+stock.getNumber() > stock.getUpperAlert()){
                return ResultObj.ADD_ERROR_EXCEED;
            }else if(inputFormVo.getChangeNumber() ==0){
                return ResultObj.ERROR_ZERO;
            } else {
                stock.setNumber(inputFormVo.getChangeNumber()+stock.getNumber());
            }
            inputFormService.save(inputFormVo);
            stockService.update(stock,updateWrapper);
            return ResultObj.ADD_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("deleteInput")
    public ResultObj deleteInput(Integer id){
        try {
            inputFormService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

