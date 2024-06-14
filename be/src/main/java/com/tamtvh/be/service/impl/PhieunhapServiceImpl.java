package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.PhieunhapDTO;
import com.tamtvh.be.mapper.PhieunhapMapper;
import com.tamtvh.be.model.Phieunhap;
import com.tamtvh.be.repository.PhieunhapRepository;
import com.tamtvh.be.service.PhieunhapService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class PhieunhapServiceImpl extends AbstractServiceImpl<PhieunhapRepository, PhieunhapMapper, PhieunhapDTO, Phieunhap>
        implements PhieunhapService {
    @Autowired
    PhieunhapRepository thisRepository;

    private PhieunhapMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PhieunhapRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public PhieunhapMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
