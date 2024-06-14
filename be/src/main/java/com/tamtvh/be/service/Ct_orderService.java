package com.tamtvh.be.service;

import com.tamtvh.be.dto.Ct_orderDTO;
import com.tamtvh.be.model.Ct_order;

import java.util.List;

public interface Ct_orderService extends AbstractService<Ct_orderDTO, Ct_order>{
    List<Ct_orderDTO> customFindAll();
}
