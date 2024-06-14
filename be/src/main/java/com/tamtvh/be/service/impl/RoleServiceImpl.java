package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.RoleDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.RoleMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Role;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.RoleRepository;
import com.tamtvh.be.service.RoleService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class RoleServiceImpl extends AbstractServiceImpl<RoleRepository, RoleMapper, RoleDTO, Role>
        implements RoleService {
    @Autowired
    RoleRepository thisRepository;

    private RoleMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public RoleRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public RoleMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public List<RoleDTO> customFindAll(){
        List<RoleDTO> dtoList = new ArrayList<>();
        List<Role> list = thisRepository.findAll();
        list.forEach(item -> {
            RoleDTO roleDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(roleDTO);
        });
        return dtoList;
    }
}
