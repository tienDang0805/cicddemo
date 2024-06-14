package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateCtPromoDTO;
import com.tamtvh.be.create.CreatePhieudatDTO;
import com.tamtvh.be.dto.Ct_promotionDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.mapper.Ct_promotionMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Ct_promotion;
import com.tamtvh.be.service.Ct_promotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/ct-promotion")
public class Ct_promotionController extends AbstractController<Ct_promotionService, Ct_promotionMapper, Ct_promotionDTO, Ct_promotion> {

    @Autowired
    Ct_promotionService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public Ct_promotionService getService() {
        initService();
        return service;
    }

    private Ct_promotionMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_promotionMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<Ct_promotionDTO> getAllCtpromo() {
        return thisService.customFindAll();
    }
    @Transactional
    @PostMapping("")
    public void create(@RequestBody CreateCtPromoDTO payload){
        getService().create(payload);
    }

    @Transactional
    @DeleteMapping(value = "MAKM={MAKM}&MADONG={MADONG}")
    public ResponseEntity<?> delete(@PathVariable("MAKM") String MAKM, @PathVariable("MADONG") String MADONG){
        return thisService.deleteCtp(MAKM, MADONG);
    }
}
