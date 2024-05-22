package com.gdpu.service.impl;

import com.gdpu.bean.Stock;
import com.gdpu.mapper.StockMapper;
import com.gdpu.service.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Resource
    StockMapper stockMapper;

    @Override
    public void updateByIds(Stock stock) {
        stockMapper.updateByIds(stock);
    }
}
