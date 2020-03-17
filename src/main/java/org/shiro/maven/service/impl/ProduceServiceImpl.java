package org.shiro.maven.service.impl;

import com.alibaba.druid.filter.AutoLoad;
import org.shiro.maven.mapper.ProduceMapper;
import org.shiro.maven.po.Produce;
import org.shiro.maven.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    ProduceMapper produceMapper;

    @Override
    public void save(List<Produce> produceList) {
        produceMapper.saveProduce(produceList);
    }
}
