package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.TransferMapper;
import org.shiro.maven.po.Transfer;
import org.shiro.maven.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferMapper transferMapper;

    @Override
    public void saveTransfer(Transfer transfer) {
        transferMapper.saveTransfer(transfer);
    }

    @Override
    public void updateTransfer(Transfer transfer) {
        transferMapper.updateTransfer(transfer);
    }

    @Override
    public List<Transfer> findAll() {
        return transferMapper.findAll();
    }

    @Override
    public void checkTransfer(String state, Integer id) {
        transferMapper.checkTransfer(state,id);
    }
}
