package org.shiro.maven.service;

import org.shiro.maven.po.SysUser;

import java.util.List;

/**
 * 认证授权服务接口
 * Created by codingBoy on 16/11/20.
 */
public interface SysService {

    SysUser findByUserName(String username);

}