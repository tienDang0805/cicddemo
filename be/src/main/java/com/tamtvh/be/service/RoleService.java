package com.tamtvh.be.service;

import com.tamtvh.be.dto.RoleDTO;
import com.tamtvh.be.model.Role;

import java.util.List;

public interface RoleService extends AbstractService<RoleDTO, Role>{
    List<RoleDTO> customFindAll();
}
