package com.tamtvh.be.controller;

import com.tamtvh.be.dto.OrderDTO;
import com.tamtvh.be.mapper.OrderMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Order;
import com.tamtvh.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractController<OrderService, OrderMapper, OrderDTO, Order> {

    @Autowired
    OrderService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public OrderService getService() {
        initService();
        return service;
    }

    private OrderMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public OrderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<OrderDTO> getAllOrder() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        var entity = getService().findEntityById(id);
        var response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Order By Id " + id,
                response));
    }
}
