package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.FinancialMapper;
import org.shiro.maven.po.Financial;
import org.shiro.maven.service.FinancialService;
import org.shiro.maven.vo.FinancialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialServiceImpl implements FinancialService {

    @Autowired
    FinancialMapper financialMapper;

    @Override
    public void saveFinancial(List<Financial> financials){
        financialMapper.saveFinancial(financials);
    }

    @Override
    public List<Financial> findAll() {
        return financialMapper.findAll();
    }

    @Override
    public Financial findById(Integer id) {
        return financialMapper.findById(id);
    }

    @Override
    public void updateFinancial(Financial financial) {
        financialMapper.updateFinancial(financial);
    }

    @Override
    public List<FinancialVo> findAllFinancialVo() {
        return financialMapper.findAllFinancialVo();
    }

    @Override
    public void deleteById(Integer id) {
        financialMapper.deleteById(id);
    }


}
