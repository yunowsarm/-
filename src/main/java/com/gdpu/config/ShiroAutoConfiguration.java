package com.gdpu.config;
import javax.servlet.Filter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.gdpu.bean.Manager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.gdpu.realm.UserRealm;
import lombok.Data;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(value = {SecurityManager.class})
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroAutoConfiguration {
    private static final String SHIRO_DIALECT = "shiroDialect";
    private static final String SHIRO_FILTER = "shiroFilter";
    private String loginURL = "/index.html";    //默认登陆页面

    private String[] anonUrls;
    private String logoutUrl;
    private String[] authcUrls;



    /*声明userRealm*/
    @Bean("userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    /*配置SecurityManager*/
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*配置shiro过滤器*/
    @Bean(SHIRO_FILTER)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        //设置未登录的时候
        factoryBean.setLoginUrl(loginURL);
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        //设置放行的路径
        if (anonUrls != null && anonUrls.length > 0) {
            for (String anon : anonUrls) {
                filterChainDefinitionMap.put(anon, "anon");
            }
        }
        //设置放行的路径
        if (null != logoutUrl) {
            filterChainDefinitionMap.put(logoutUrl, "logout");
        }
        //设置放行的路径
        if (authcUrls != null && anonUrls.length > 0) {
            for (String authc : authcUrls) {
                filterChainDefinitionMap.put(authc, "authc");
            }
        }
        Map<String, Filter> filters = new HashMap<>();
        factoryBean.setFilters(filters);
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }


    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxyFilter() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName(SHIRO_FILTER);
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean(name = SHIRO_DIALECT)
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
