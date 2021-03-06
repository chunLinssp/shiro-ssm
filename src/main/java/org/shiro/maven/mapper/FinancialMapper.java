package org.shiro.maven.mapper;

import org.shiro.maven.po.Financial;
import org.shiro.maven.vo.FinancialVo;

import java.util.List;

public interface FinancialMapper {

    void saveFinancial(List<Financial> financials);

    List<Financial> findAll();

    Financial findById(Integer id);

    void updateFinancial(Financial financial);

    List<FinancialVo> findAllFinancialVo();

    void deleteById(Integer id);
}
