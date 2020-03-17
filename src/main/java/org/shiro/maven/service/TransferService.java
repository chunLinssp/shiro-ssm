package org.shiro.maven.service;

import org.shiro.maven.po.Transfer;

import java.util.List;

public interface TransferService {

    void saveTransfer(Transfer transfer);

    void updateTransfer(Transfer transfer);

    List<Transfer> findAll();

    void checkTransfer(String state, Integer id );
}
