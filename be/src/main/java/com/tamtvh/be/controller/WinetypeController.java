package com.tamtvh.be.controller;


import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateWinetypeDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.WinetypeMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.service.WinetypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/winetype")
public class WinetypeController extends AbstractController<WinetypeService, WinetypeMapper, WinetypeDTO, Winetype> {

    @Autowired
    WinetypeService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public WinetypeService getService() {
        initService();
        return service;
    }

    private WinetypeMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public WinetypeMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<WinetypeDTO> getAllWinetype() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public WinetypeDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateWinetypeDTO createWinetypeDTO){
        return thisService.create(createWinetypeDTO);
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody CreateWinetypeDTO updateWinetypeDTO){
        return thisService.update(id ,updateWinetypeDTO);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return thisService.deleteWt(id);
    }
}
