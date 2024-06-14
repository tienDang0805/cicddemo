package com.tamtvh.be.service;

import com.tamtvh.be.dto.OrderDTO;
import com.tamtvh.be.model.Order;

import java.util.List;

public interface OrderService extends AbstractService<OrderDTO, Order>{
    List<OrderDTO> customFindAll();
}
