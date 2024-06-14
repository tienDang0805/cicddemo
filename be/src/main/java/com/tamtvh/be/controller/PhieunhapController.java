package com.tamtvh.be.controller;

import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.PhieunhapDTO;
import com.tamtvh.be.mapper.PhieudatMapper;
import com.tamtvh.be.mapper.PhieunhapMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Phieunhap;
import com.tamtvh.be.service.PhieudatService;
import com.tamtvh.be.service.PhieunhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/phieunhap")
public class PhieunhapController extends AbstractController<PhieunhapService, PhieunhapMapper, PhieunhapDTO, Phieunhap> {

    @Autowired
    PhieunhapService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public PhieunhapService getService() {
        initService();
        return service;
    }

    private PhieunhapMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PhieunhapMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPn() {
        return getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPnById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get User By Id " + id,
                response));
    }
}
