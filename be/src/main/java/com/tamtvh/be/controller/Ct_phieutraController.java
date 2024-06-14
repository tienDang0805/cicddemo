package com.tamtvh.be.controller;

import com.tamtvh.be.dto.Ct_phieutraDTO;
import com.tamtvh.be.mapper.Ct_phieutraMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Ct_phieutra;
import com.tamtvh.be.service.Ct_phieutraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ct-phieutra")
public class Ct_phieutraController extends AbstractController<Ct_phieutraService, Ct_phieutraMapper, Ct_phieutraDTO, Ct_phieutra> {

    @Autowired
    Ct_phieutraService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public Ct_phieutraService getService() {
        initService();
        return service;
    }

    private Ct_phieutraMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_phieutraMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCtpt() {
        return getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPtById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Ctpt By Id " + id,
                response));
    }
}
