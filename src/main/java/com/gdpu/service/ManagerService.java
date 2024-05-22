package com.gdpu.service;

import com.gdpu.bean.Manager;
import com.baomidou.mybatisplus.extension.service.IService;
public interface ManagerService extends IService<Manager> {
    Manager getByAccount(String account);
}
