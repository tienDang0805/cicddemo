package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateCustomerDTO;
import com.tamtvh.be.create.CreatePhieudatDTO;
import com.tamtvh.be.create.UpdateCustomerDTO;
import com.tamtvh.be.dto.CustomerDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.mapper.CustomerMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Customer;
import com.tamtvh.be.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends AbstractController<CustomerService, CustomerMapper, CustomerDTO, Customer> {

    @Autowired
    CustomerService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public CustomerService getService() {
        initService();
        return service;
    }

    private CustomerMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CustomerMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<CustomerDTO> getAllUser() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }
    @Transactional
    @PostMapping("")
    public void create(@RequestBody CreateCustomerDTO payload){
        thisService.create(payload);
    }
    @Transactional
    @PutMapping("/detail/{id}")
    public void updateCustomerDetail(@PathVariable("id") String id, @RequestBody UpdateCustomerDTO payload){
        thisService.updateCustomerDetail(id, payload);
    }
    @Transactional
    @PutMapping("/pass/detail/pleaseeeeeee/{MAKH}")
    public void updateCustomerPassword(@PathVariable("MAKH") String MAKH, @RequestBody UpdateCustomerDTO payload){
        thisService.updateCustomerPass(MAKH, payload);
    }
}
