package org.shiro.maven.mapper;

import org.shiro.maven.po.Produce;

import java.util.List;

public interface ProduceMapper {

    void saveProduce(List<Produce> produceList);
}
