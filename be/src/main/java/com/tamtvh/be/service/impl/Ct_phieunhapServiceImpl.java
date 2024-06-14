package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.Ct_phieunhapDTO;
import com.tamtvh.be.mapper.Ct_phieunhapMapper;
import com.tamtvh.be.model.Ct_phieunhap;
import com.tamtvh.be.repository.Ct_phieunhapRepository;
import com.tamtvh.be.service.Ct_phieunhapService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class Ct_phieunhapServiceImpl extends AbstractServiceImpl<Ct_phieunhapRepository, Ct_phieunhapMapper, Ct_phieunhapDTO, Ct_phieunhap>
        implements Ct_phieunhapService {
    @Autowired
    Ct_phieunhapRepository thisRepository;

    private Ct_phieunhapMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_phieunhapRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public Ct_phieunhapMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
