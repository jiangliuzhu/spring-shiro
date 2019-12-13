package com.itheima.springbootshiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author:jlz
 * @Date: 2019/12/13
 * 路径： com.itheima.springbootshiro.realm
 * 功能描述：
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * @author: jlz
     * 功能描述：执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }
    /**
     * @author: jlz
     * 功能描述：执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设数据库的用户名和密码
        String name = "tom";
        String password = "123";
        //编写shiro判断逻辑，判断用户名和密码
        //1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(!token.getUsername().equals(name)){
            //用户名不存在
            return null;//shiro底层会抛出UnknownAccountException
        }
        //2、判断密码
        return new SimpleAuthenticationInfo("",password,"");
    }
}
