package org.shiro.maven.service;

import org.shiro.maven.po.Department;

import java.util.List;

public interface DepartmentService {

    Department findBydId(Integer id);

    List<Department> findAll();

    Department findByName(String name);

}
