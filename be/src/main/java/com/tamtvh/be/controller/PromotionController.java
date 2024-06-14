package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.create.CreatePromoDTO;
import com.tamtvh.be.dto.PromotionDTO;
import com.tamtvh.be.mapper.PromotionMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Promotion;
import com.tamtvh.be.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController extends AbstractController<PromotionService, PromotionMapper, PromotionDTO, Promotion> {

    @Autowired
    PromotionService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public PromotionService getService() {
        initService();
        return service;
    }

    private PromotionMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PromotionMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<PromotionDTO> getAllPromo() {
        return thisService.customFindAll();
    }
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreatePromoDTO createPromoDTO){
        return thisService.create(createPromoDTO);
    }

    @GetMapping("/{id}")
    public PromotionDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }

    @GetMapping("/curPromo/cur")
    public PromotionDTO getCurrentPromo() {
        return thisService.getCurrentPromo();
    }
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody CreatePromoDTO updatePromoDTO){
        return thisService.update(id ,updatePromoDTO);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return thisService.deletePrm(id);
    }

}
