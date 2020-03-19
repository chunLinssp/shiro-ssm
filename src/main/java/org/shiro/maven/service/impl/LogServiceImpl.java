package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.LogMapper;
import org.shiro.maven.po.Log;
import org.shiro.maven.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public void saveLog(Log log) {
        logMapper.saveLog(log);
    }
}
