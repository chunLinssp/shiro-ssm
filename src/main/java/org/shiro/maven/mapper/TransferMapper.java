package org.shiro.maven.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiro.maven.po.Transfer;

import java.util.List;

public interface TransferMapper {

    void saveTransfer(Transfer transfer);

    void updateTransfer(Transfer transfer);

    List<Transfer> findAll();

    void checkTransfer(@Param("state")String state, @Param("id") Integer id );


}
