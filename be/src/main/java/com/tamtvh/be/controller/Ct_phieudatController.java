package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateCtPhieudatDTO;
import com.tamtvh.be.create.CtpdFromTo;
import com.tamtvh.be.create.TotalRev;
import com.tamtvh.be.dto.Ct_phieudatDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.mapper.Ct_phieudatMapper;
import com.tamtvh.be.mapper.PhieudatMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Ct_phieudat;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.service.Ct_phieudatService;
import com.tamtvh.be.service.PhieudatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ct-phieudat")
public class Ct_phieudatController extends AbstractController<Ct_phieudatService, Ct_phieudatMapper, Ct_phieudatDTO, Ct_phieudat> {

    @Autowired
    Ct_phieudatService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public Ct_phieudatService getService() {
        initService();
        return service;
    }

    private Ct_phieudatMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_phieudatMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<Ct_phieudatDTO> getAllCtpd() {
        return thisService.customFindAll();
    }
    @Transactional
    @PostMapping("")
    public void create(@RequestBody CreateCtPhieudatDTO payload){
        thisService.create(payload);
    }

    @GetMapping("/{from},{to}")
    public List<CtpdFromTo> getRevProduct(@PathVariable("from") String from, @PathVariable("to") String to){
        return thisService.getRevenueProduct(from,to);
    }

    @GetMapping("/total/{from},{to}")
    public List<TotalRev> getTotalRev(@PathVariable("from") String from, @PathVariable("to") String to){
        return thisService.getTotalRev(from,to);
    }


}
