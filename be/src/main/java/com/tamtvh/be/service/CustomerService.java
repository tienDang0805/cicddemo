package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateCustomerDTO;
import com.tamtvh.be.create.UpdateCustomerDTO;
import com.tamtvh.be.dto.CustomerDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.model.Customer;

import java.util.List;

public interface CustomerService extends AbstractService<CustomerDTO, Customer>{
    CustomerDTO findById(String MAKH);
    Customer findByUsername(String USERNAME);

    void create(CreateCustomerDTO payload);

    void updateCustomerDetail(String id, UpdateCustomerDTO payload);

    void  updateCustomerPass(String MAKH, UpdateCustomerDTO payload);

    List<CustomerDTO> customFindAll();
}
