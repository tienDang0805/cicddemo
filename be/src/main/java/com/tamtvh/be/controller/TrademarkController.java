package com.tamtvh.be.controller;


import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.create.CreateTrademarkDTO;
import com.tamtvh.be.dto.TrademarkDTO;
import com.tamtvh.be.mapper.TrademarkMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Trademark;
import com.tamtvh.be.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trademarks")
public class TrademarkController extends AbstractController<TrademarkService, TrademarkMapper, TrademarkDTO, Trademark> {

    @Autowired
    TrademarkService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public TrademarkService getService() {
        initService();
        return service;
    }

    private TrademarkMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public TrademarkMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<TrademarkDTO> getAllTrademark() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public TrademarkDTO getTrademarkById(@PathVariable String id) {
        return thisService.findByMATH(id);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateTrademarkDTO createTrademarkDTO){
        return thisService.create(createTrademarkDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody CreateTrademarkDTO updateTrademarkDTO){
        return thisService.update(id ,updateTrademarkDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return thisService.deleteTM(id);
    }

}
