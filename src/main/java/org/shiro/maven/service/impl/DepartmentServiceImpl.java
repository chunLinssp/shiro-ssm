package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.DepartmentMapper;
import org.shiro.maven.po.Department;
import org.shiro.maven.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public Department findBydId(Integer id) {
        return departmentMapper.findBydId(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public Department findByName(String name) {
        return departmentMapper.findByName(name);
    }

}
