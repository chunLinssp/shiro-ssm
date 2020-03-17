package org.shiro.maven.mapper;

import org.shiro.maven.po.SysUser;

public interface SysUserMapper {
    SysUser findByUserName(String username);
}
