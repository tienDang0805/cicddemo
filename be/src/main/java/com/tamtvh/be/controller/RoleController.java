package com.tamtvh.be.controller;

import com.tamtvh.be.dto.RoleDTO;
import com.tamtvh.be.mapper.RoleMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Role;
import com.tamtvh.be.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/role")
public class RoleController extends AbstractController<RoleService, RoleMapper, RoleDTO, Role> {

    @Autowired
    RoleService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public RoleService getService() {
        initService();
        return service;
    }

    private RoleMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public RoleMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public List<RoleDTO> getAllRole() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Role By Id " + id,
                response));
    }
}
