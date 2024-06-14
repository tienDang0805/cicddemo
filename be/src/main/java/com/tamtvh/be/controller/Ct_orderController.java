package com.tamtvh.be.controller;

import com.tamtvh.be.dto.Ct_orderDTO;
import com.tamtvh.be.mapper.Ct_orderMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Ct_order;
import com.tamtvh.be.service.Ct_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ct-order")
public class Ct_orderController extends AbstractController<Ct_orderService, Ct_orderMapper, Ct_orderDTO, Ct_order> {

    @Autowired
    Ct_orderService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public Ct_orderService getService() {
        initService();
        return service;
    }

    private Ct_orderMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_orderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<Ct_orderDTO> getAllCtod() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCtodById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Ctod By Id " + id,
                response));
    }
}
