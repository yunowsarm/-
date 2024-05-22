package com.gdpu.mapper;

import com.gdpu.bean.Manager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ManagerMapper extends BaseMapper<Manager> {

    Manager getByAccount(String account);
}
