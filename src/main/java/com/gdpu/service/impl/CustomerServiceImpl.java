package com.gdpu.service.impl;

import com.gdpu.bean.Customer;
import com.gdpu.mapper.CustomerMapper;
import com.gdpu.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-07-02
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
