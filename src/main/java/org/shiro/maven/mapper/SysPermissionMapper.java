package org.shiro.maven.mapper;

import org.shiro.maven.po.SysPermission;

import java.util.List;

public interface SysPermissionMapper {

    List<SysPermission> findById(List<Integer> id);

}
