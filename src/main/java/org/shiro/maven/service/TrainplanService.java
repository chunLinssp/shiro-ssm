package org.shiro.maven.service;

import org.shiro.maven.po.Trainplan;
import org.shiro.maven.vo.TrainplanVo;

import java.util.Date;
import java.util.List;

public interface TrainplanService {

    void saveTrainplan(Trainplan trainplan);

    void updateTrainplane(Trainplan trainplan);

    List<TrainplanVo> findAll();

    void deleteOne(Integer id);

    void checkTrainplane(String state, Integer id, Date date);

    TrainplanVo findVoById(Integer id);

    Integer WillChecked();
}
