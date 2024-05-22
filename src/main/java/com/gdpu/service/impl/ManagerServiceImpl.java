package com.gdpu.service.impl;

import com.gdpu.bean.Manager;
import com.gdpu.mapper.ManagerMapper;
import com.gdpu.service.ManagerService;
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
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Resource
    ManagerMapper managerMapper;

    @Override
    public Manager getByAccount(String account) {
        return managerMapper.getByAccount(account);
    }
}
