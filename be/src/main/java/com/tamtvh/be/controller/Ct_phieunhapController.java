package com.tamtvh.be.controller;

import com.tamtvh.be.dto.Ct_phieunhapDTO;
import com.tamtvh.be.mapper.Ct_phieunhapMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Ct_phieunhap;
import com.tamtvh.be.service.Ct_phieunhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ct_phieunhap")
public class Ct_phieunhapController extends AbstractController<Ct_phieunhapService, Ct_phieunhapMapper, Ct_phieunhapDTO, Ct_phieunhap> {

    @Autowired
    Ct_phieunhapService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public Ct_phieunhapService getService() {
        initService();
        return service;
    }

    private Ct_phieunhapMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_phieunhapMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCtpn() {
        return getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCtpnById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Ctpn By Id " + id,
                response));
    }
}
