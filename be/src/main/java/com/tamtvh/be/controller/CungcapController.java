package com.tamtvh.be.controller;

import com.tamtvh.be.dto.CungcapDTO;
import com.tamtvh.be.mapper.CungcapMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Cungcap;
import com.tamtvh.be.service.CungcapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cungcap")
public class CungcapController extends AbstractController<CungcapService, CungcapMapper, CungcapDTO, Cungcap> {

    @Autowired
    CungcapService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public CungcapService getService() {
        initService();
        return service;
    }

    private CungcapMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CungcapMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<CungcapDTO> getAllCungcap() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCungcapById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Cungcap By Id " + id,
                response));
    }
}
