package org.shiro.maven.service;

import org.shiro.maven.po.SalePeople;
import org.shiro.maven.vo.SalePeopleVo;

import java.util.List;

public interface SaleService {

    void saveSale(List<SalePeople> salePeople);

    //近30天排行
    List<SalePeopleVo> findTop5AsMonth();

    //近7天排行
    List<SalePeopleVo> findTop5AsWeekend();

    //昨天排行
    List<SalePeopleVo> findTop5AsYesterday();
}
