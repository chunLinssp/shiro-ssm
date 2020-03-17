package org.shiro.maven.mapper;

import org.shiro.maven.po.Personinf;
import org.shiro.maven.vo.PersoninfVo;

import java.util.List;

public interface PersoninfMapper {
    Personinf findById(Integer id);

    List<Personinf> findAll();

    void updateOne(Personinf personinf);

    void delteOne(Integer id);

    void save(List<Personinf> personinfs);

    List<PersoninfVo> findAllPersoninfVo();

    PersoninfVo findVoById(Integer id);

    void saveVo(List<PersoninfVo> personinfVos);
}
