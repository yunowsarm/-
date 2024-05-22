package com.gdpu.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdpu.bean.Manager;
import com.gdpu.common.ActiveUser;
import com.gdpu.service.ManagerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

    @Resource
    private ManagerService managerService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /*
     *   授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /*
     *   认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // mybatis-plus 的条件构造器，用于筛选相同的账号
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        // 在数据库中筛选账号相同的用户
        queryWrapper.eq("account", token.getPrincipal().toString());
        Manager manager = managerService.getOne(queryWrapper);

        if (manager != null) {
            // 新建活动用户
            ActiveUser activeUser = new ActiveUser();
            activeUser.setManager(manager);

            // 直接使用数据库中的明文密码
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, manager.getPassword(), this.getName());
            return info;
        }

        return null;
    }
}
