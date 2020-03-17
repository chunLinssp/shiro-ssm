package org.shiro.maven.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiro.maven.po.Trainplan;
import org.shiro.maven.vo.TrainplanVo;

import java.util.Date;
import java.util.List;

public interface TrainplanMapper {

    void saveTrainplan(Trainplan trainplan);

    void updateTrainplane(Trainplan trainplan);

    List<TrainplanVo> findAll();

    void deleteOne(Integer id);

    void checkTrainplane(@Param("state")String state, @Param("id") Integer id, @Param("date")Date date);

    TrainplanVo findVoById(Integer id);

    //待审批数量
    Integer WillChecked();
}
