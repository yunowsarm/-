package com.gdpu.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdpu.common.DataGridView;
import com.gdpu.common.ResultObj;
import com.gdpu.service.ManagerService;
import com.gdpu.vo.ManagerVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@RequestMapping("/user")
public class ManagerController {
    @Resource
    private ManagerService managerService;

    /*查询用户*/
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(ManagerVo userVo) {
        IPage<com.gdpu.bean.Manager> page = new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<com.gdpu.bean.Manager> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(userVo.getAccount()), "account", userVo.getAccount());
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getSex()), "sex", userVo.getSex());
        managerService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    @RequestMapping("addUser")
    public ResultObj addUser(ManagerVo userVo){
        try{
            String account = userVo.getAccount();
            com.gdpu.bean.Manager user = managerService.getByAccount(account);
            if(null != user){
                return ResultObj.ADD_ERROR_EXIST;
            }
            //设置盐
            //String salt = IdUtil.simpleUUID().toUpperCase();
            //userVo.setSalt(salt);
            //设置密码
            //userVo.setPassword(new Md5Hash(userVo.getPassword(),salt,2).toString());
            userVo.setPassword(userVo.getPassword());
            //设置性别
            String sex = userVo.getSex()=="1"?"男":"女";
            userVo.setSex(sex);
            managerService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("updateUser")
    public ResultObj updateUser(ManagerVo userVo){
        try {
        //String salt = ManagerService.getById(userVo.getUserId()).getSalt();
        //userVo.setPassword(new Md5Hash(userVo.getPassword(),salt,2).toString());
            userVo.setPassword(userVo.getPassword());
        //设置性别
            String sex = "1".equals(userVo.getSex())?"男":"女";
            userVo.setSex(sex);
            managerService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id){
        try {
            this.managerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

