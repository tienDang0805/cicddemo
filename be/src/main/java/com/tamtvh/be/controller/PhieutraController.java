package com.tamtvh.be.controller;

import com.tamtvh.be.dto.PhieunhapDTO;
import com.tamtvh.be.dto.PhieutraDTO;
import com.tamtvh.be.mapper.PhieunhapMapper;
import com.tamtvh.be.mapper.PhieutraMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Phieunhap;
import com.tamtvh.be.model.Phieutra;
import com.tamtvh.be.service.PhieunhapService;
import com.tamtvh.be.service.PhieutraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/phieutra")
public class PhieutraController extends AbstractController<PhieutraService, PhieutraMapper, PhieutraDTO, Phieutra> {

    @Autowired
    PhieutraService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public PhieutraService getService() {
        initService();
        return service;
    }

    private PhieutraMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PhieutraMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPt() {
        return getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPtById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Pt By Id " + id,
                response));
    }
}
