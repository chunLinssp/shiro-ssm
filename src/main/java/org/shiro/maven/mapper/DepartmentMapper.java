package org.shiro.maven.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiro.maven.po.Department;

import java.util.List;

public interface DepartmentMapper {
    Department findBydId(Integer id);

    List<Department> findAll();

    Department findByName(@Param("depName") String depName);
}
