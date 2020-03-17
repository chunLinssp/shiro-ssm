package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.TrainplanMapper;
import org.shiro.maven.po.Trainplan;
import org.shiro.maven.service.TrainplanService;
import org.shiro.maven.vo.TrainplanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainplanServiceImpl implements TrainplanService{

    @Autowired
    TrainplanMapper trainplanMapper;


    @Override
    public void saveTrainplan(Trainplan trainplan) {
        trainplanMapper.saveTrainplan(trainplan);
    }

    @Override
    public void updateTrainplane(Trainplan trainplan) {
        trainplanMapper.updateTrainplane(trainplan);
    }

    @Override
    public List<TrainplanVo> findAll() {
        return trainplanMapper.findAll();
    }

    @Override
    public void deleteOne(Integer id) {
        trainplanMapper.deleteOne(id);
    }

    @Override
    public void checkTrainplane(String state, Integer id, Date date) {
        trainplanMapper.checkTrainplane(state,id,date);
    }

    @Override
    public TrainplanVo findVoById(Integer id) {
        return trainplanMapper.findVoById(id);
    }

    @Override
    public Integer WillChecked() {
        return trainplanMapper.WillChecked();
    }
}
