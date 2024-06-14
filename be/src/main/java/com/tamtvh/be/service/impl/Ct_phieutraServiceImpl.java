package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.Ct_phieutraDTO;
import com.tamtvh.be.mapper.Ct_phieutraMapper;
import com.tamtvh.be.model.Ct_phieutra;
import com.tamtvh.be.repository.Ct_phieutraRepository;
import com.tamtvh.be.service.Ct_phieutraService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class Ct_phieutraServiceImpl extends AbstractServiceImpl<Ct_phieutraRepository, Ct_phieutraMapper, Ct_phieutraDTO, Ct_phieutra>
        implements Ct_phieutraService {
    @Autowired
    Ct_phieutraRepository thisRepository;

    private Ct_phieutraMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_phieutraRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public Ct_phieutraMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
