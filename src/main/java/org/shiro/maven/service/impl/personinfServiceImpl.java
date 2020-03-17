package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.PersoninfMapper;
import org.shiro.maven.po.Personinf;
import org.shiro.maven.service.PersoninfService;
import org.shiro.maven.vo.PersoninfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class personinfServiceImpl implements PersoninfService {

    @Autowired
    PersoninfMapper personinfMapper;

    @Override
    public Personinf findById(Integer id) {
        return personinfMapper.findById(id);
    }

    @Override
    public List<Personinf> findAll() {
        return personinfMapper.findAll();
    }

    @Override
    public void updateOne(Personinf personinf) {
        personinfMapper.updateOne(personinf);
    }



    @Override
    public void delteOne(Integer id) {
        personinfMapper.delteOne(id);
    }

    @Override
    public void save(List<Personinf> personinfs) {
        personinfMapper.save(personinfs);
    }

    @Override
    public List<PersoninfVo> findAllPersoninfVo() {
        return personinfMapper.findAllPersoninfVo();
    }

    @Override
    public PersoninfVo findVoById(Integer id) {
        return personinfMapper.findVoById(id);
    }

    @Override
    public void saveVo(List<PersoninfVo> personinfVos) {
        personinfMapper.saveVo(personinfVos);
    }
}
