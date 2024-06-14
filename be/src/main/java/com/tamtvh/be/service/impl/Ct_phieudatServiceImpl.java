package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateCtPhieudatDTO;
import com.tamtvh.be.create.CtpdFromTo;
import com.tamtvh.be.create.TotalRev;
import com.tamtvh.be.dto.Ct_phieudatDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.Ct_phieudatMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.*;
import com.tamtvh.be.repository.Ct_phieudatRepository;
import com.tamtvh.be.service.Ct_phieudatService;
import com.tamtvh.be.service.PhieudatService;
import com.tamtvh.be.service.WinelineService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ServiceHelper
public class Ct_phieudatServiceImpl extends AbstractServiceImpl<Ct_phieudatRepository, Ct_phieudatMapper, Ct_phieudatDTO, Ct_phieudat>
        implements Ct_phieudatService {
    @Autowired
    Ct_phieudatRepository thisRepository;

    private Ct_phieudatMapper thisMapper;

    @Autowired
    WinelineService winelineService;


    PhieudatService phieudatService;
    @Autowired
    public Ct_phieudatServiceImpl(@Lazy PhieudatService phieudatService){
        this.phieudatService = phieudatService;
    }

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_phieudatRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public Ct_phieudatMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public List<CtpdFromTo> getRevenueProduct(String from, String to){

        return thisRepository.getRevenueProduct(from,to)
                .stream().map(CtpdFromTo::new).collect(Collectors.toList());
    }

    public List<TotalRev> getTotalRev(String from, String to){

        return thisRepository.getTotalRev(from,to)
                .stream().map(TotalRev::new).collect(Collectors.toList());
    }

    public void create(CreateCtPhieudatDTO payload){
        Ct_phieudat ctPhieudat = new Ct_phieudat();
        Wineline wineline = winelineService.findEntityById(payload.getMADONG());
        Phieudat phieudat = phieudatService.findEntityById(payload.getMAPD());
        ctPhieudat.setPhieudat(phieudat);
        ctPhieudat.setWineline(wineline);
        ctPhieudat.setSOLUONG(payload.getSOLUONG());
        ctPhieudat.setGIA(payload.getGIA());
        this.save(ctPhieudat);
    }

    public List<Ct_phieudatDTO> customFindAll(){
        List<Ct_phieudatDTO> dtoList = new ArrayList<>();
        List<Ct_phieudat> list = thisRepository.findAll();
        list.forEach(item -> {
            Ct_phieudatDTO ctPhieudatDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(ctPhieudatDTO);
        });
        return dtoList;
    }
}
