package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateTrademarkDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.mapper.ProviderMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Provider;
import com.tamtvh.be.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/provider")
public class ProviderController extends AbstractController<ProviderService, ProviderMapper, ProviderDTO, Provider> {

    @Autowired
    ProviderService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ProviderService getService() {
        initService();
        return service;
    }

    private ProviderMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ProviderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<ProviderDTO> getAllProvider() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public ProviderDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateProviderDTO createProviderDTO){
        return thisService.create(createProviderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody CreateProviderDTO updateProviderDTO){
        return thisService.update(id ,updateProviderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return thisService.deletePvd(id);
    }
}
