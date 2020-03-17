package org.shiro.maven.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiro.maven.po.RoleTmp;
import org.shiro.maven.po.SysRole;

import java.util.List;
import java.util.Set;

public interface SysRoleMapper {

    List<RoleTmp> findById(Integer id);

    List<SysRole> findByListId (@Param("set") Set<String> roleIds);
}
