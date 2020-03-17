package org.shiro.maven.service;

import org.shiro.maven.po.Produce;

import java.util.List;

public interface ProduceService {
    void save(List<Produce> produceList);
}
