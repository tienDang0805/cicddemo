package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.PhieutraDTO;
import com.tamtvh.be.mapper.PhieunhapMapper;
import com.tamtvh.be.mapper.PhieutraMapper;
import com.tamtvh.be.model.Phieutra;
import com.tamtvh.be.repository.PhieunhapRepository;
import com.tamtvh.be.repository.PhieutraRepository;
import com.tamtvh.be.service.PhieutraService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class PhieutraServiceImpl extends AbstractServiceImpl<PhieutraRepository, PhieutraMapper, PhieutraDTO, Phieutra>
        implements PhieutraService {
    @Autowired
    PhieutraRepository thisRepository;

    private PhieutraMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PhieutraRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public PhieutraMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
