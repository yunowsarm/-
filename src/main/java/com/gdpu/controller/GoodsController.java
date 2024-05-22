package com.gdpu.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdpu.bean.Goods;
import com.gdpu.bean.Provider;
import com.gdpu.bean.Stock;
import com.gdpu.common.DataGridView;
import com.gdpu.common.ResultObj;
import com.gdpu.service.GoodsService;
import com.gdpu.service.ProviderService;
import com.gdpu.service.StockService;
import com.gdpu.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @Resource
    private ProviderService providerService;

    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo){
        IPage<Goods> page = new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getName()),"name",goodsVo.getName());
        queryWrapper.eq(goodsVo.getPrice()!=null,"price",goodsVo.getPrice());
        queryWrapper.eq(null != goodsVo.getProviderId() && goodsVo.getProviderId()!=0,"provider_id",goodsVo.getProviderId());
        goodsService.page(page,queryWrapper);
        List<Goods> records = page.getRecords();
        for (Goods goods : records) {
            Provider provider = providerService.getById(goods.getProviderId());
            if (null!=provider){
                goods.setProviderName(provider.getName());
                goods.setAddress(provider.getAddress());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("addGoods")
    public ResultObj addGoods(GoodsVo goodsVo){
        try {
            goodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo){
        try {
            goodsService.updateById(goodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id){
        try {
            goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    //提供下拉框
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllProviderForSelect(){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>();
        List<Goods> list = goodsService.list(queryWrapper);
        return new DataGridView(list);
    }

    //提供下拉框
    @RequestMapping("loadAllGoodsForSpecialSelect")
    public DataGridView loadAllProviderForSpecialSelect(Integer providerId){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>();
        queryWrapper.eq(null!=providerId,"provider_id",providerId);
        List<Goods> list = goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = providerService.getById(goods.getProviderId());
            if (null!=provider){
                goods.setProviderName(provider.getName());
            }
        }
        return new DataGridView(list);
    }
}

