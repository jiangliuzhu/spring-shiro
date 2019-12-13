package com.itheima.springbootshiro.config;

import com.itheima.springbootshiro.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author:jlz
 * @Date: 2019/12/13
 * 路径： com.itheima.springbootshiro.config
 * 功能描述：
 */
@Configuration
public class ShiroConfig {
    /**
     * @author: jlz
     * 功能描述：ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器
        /**
            * @author: jlz
            * 功能描述：
         Shiro内置过滤器，可以实现权限相关的拦截器
            常用的过滤器：
                anon：无需认证（登录）可以访问
                authc：必须认证才可以访问
                user：如果使用rememberMe的功能可以直接访问
                perms：该资源必须得到资源权限才可以访问
                role：该资源必须得到角色权限才可以访问
            */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/

        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/login","anon");
        filterMap.put("/*","authc");
        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     * @author: jlz
     * 功能描述：DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
        * @author: jlz
        * 功能描述：创建Realm
        */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
