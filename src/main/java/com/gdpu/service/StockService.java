package com.gdpu.service;

import com.gdpu.bean.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
public interface StockService extends IService<Stock> {

    void updateByIds(Stock stock);
}
