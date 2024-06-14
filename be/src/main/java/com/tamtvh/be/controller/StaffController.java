package com.tamtvh.be.controller;

import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.mapper.StaffMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/staffs")
public class StaffController extends AbstractController<StaffService, StaffMapper, StaffDTO, Staff> {

    @Autowired
    StaffService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public StaffService getService() {
        initService();
        return service;
    }

    private StaffMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public StaffMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<StaffDTO> getAllUser() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public StaffDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }

    @GetMapping("/nvgh")
    public List<StaffDTO> GetNVGH() {
        return thisService.getNVGH();
    }
}
