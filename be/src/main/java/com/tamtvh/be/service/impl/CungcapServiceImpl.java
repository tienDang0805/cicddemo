package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.CungcapDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.CungcapMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Cungcap;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.CungcapRepository;
import com.tamtvh.be.service.CungcapService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class CungcapServiceImpl extends AbstractServiceImpl<CungcapRepository, CungcapMapper, CungcapDTO, Cungcap>
        implements CungcapService {
    @Autowired
    CungcapRepository thisRepository;

    private CungcapMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CungcapRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public CungcapMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public List<CungcapDTO> customFindAll(){
        List<CungcapDTO> dtoList = new ArrayList<>();
        List<Cungcap> list = thisRepository.findAll();
        list.forEach(item -> {
            CungcapDTO cungcapDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(cungcapDTO);
        });
        return dtoList;
    }
}
