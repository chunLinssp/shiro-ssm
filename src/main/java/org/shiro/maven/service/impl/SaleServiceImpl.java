package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.SalePeopleMapper;
import org.shiro.maven.po.SalePeople;
import org.shiro.maven.service.SaleService;
import org.shiro.maven.vo.SalePeopleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SalePeopleMapper salePeopleMapper;

    @Override
    public void saveSale(List<SalePeople> salePeople) {
        salePeopleMapper.saveSale(salePeople);
    }

    @Override
    public List<SalePeopleVo> findTop5AsMonth() {
        return salePeopleMapper.findTop5AsMonth();
    }

    @Override
    public List<SalePeopleVo> findTop5AsWeekend() {
        return salePeopleMapper.findTop5AsWeekend();
    }

    @Override
    public List<SalePeopleVo> findTop5AsYesterday() {
        return salePeopleMapper.findTop5AsYesterday();
    }


}
