package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateCustomerDTO;
import com.tamtvh.be.create.UpdateCustomerDTO;
import com.tamtvh.be.dto.CustomerDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.CustomerMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.*;
import com.tamtvh.be.repository.CustomerRepository;
import com.tamtvh.be.service.CustomerService;
import com.tamtvh.be.service.RoleService;
import com.tamtvh.be.service.StaffService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@ServiceHelper
public class CustomerServiceImpl extends AbstractServiceImpl<CustomerRepository, CustomerMapper, CustomerDTO, Customer>
        implements CustomerService {
    @Autowired
    CustomerRepository thisRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    RoleService roleService;

    private CustomerMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CustomerRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public CustomerMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public Customer findByUsername(String USERNAME){
        Customer customer = thisRepository.findByUSERNAME(USERNAME);
        return customer;
    }

    public CustomerDTO findById(String id) {
        Customer customer  = thisRepository.findByMAKH(id);
        CustomerDTO dto = getMapper().toDtoWithoutLists(customer, new CycleAvoidingMappingContext());
        return dto;
    }

    //not done yet
    public void create(CreateCustomerDTO payload){
        String usrname = payload.getUSERNAME();
        String pwd = payload.getPASSWORD();
        Staff staff = staffService.findByUsername(usrname);
        Customer customer = this.findByUsername(usrname);
        if(staff != null || customer != null){
            throw new ObjectNotFoundException("USERNAME exist",usrname);
        }
        String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt(10));
        payload.setPASSWORD(hashedPwd);

        Customer last = thisRepository.findCustomerMaxId();
        var id = Integer.parseInt(last.getMAKH()) + 1;
        Customer customer1 = new Customer();
        customer1.setMAKH(String.format("%03d", id));
        customer1.setHO(payload.getHO());
        customer1.setTEN(payload.getTEN());
        customer1.setGIOITINH(payload.getGIOITINH());
        customer1.setNGAYSINH(payload.getNGAYSINH());
        customer1.setDIACHI(payload.getDIACHI());
        customer1.setSDT(payload.getSDT());
        customer1.setEMAIL(payload.getEMAIL());
        customer1.setUSERNAME(payload.getUSERNAME());
        customer1.setPASSWORD(payload.getPASSWORD());
        Role role = roleService.findEntityById(payload.getMANQ());
        customer1.setRole1(role);
        this.save(customer1);
    }

    public void updateCustomerDetail(String id, UpdateCustomerDTO payload){
        String pwd = payload.getPASSWORD();
        String ho = payload.getHO();
        String[] arr = ho.split(" ");
        String kq = "", temp = "";
        for(int i = 0; i < arr.length; i++){
            temp = arr[i].trim();
            if(temp != ""){
                kq += temp + " ";
            }
        }
        kq.trim();

        Customer customer1 = this.findEntityById(id);
        customer1.setHO(kq.trim());
        customer1.setTEN(payload.getTEN());
        customer1.setGIOITINH(payload.getGIOITINH());
        customer1.setNGAYSINH(payload.getNGAYSINH());
        customer1.setDIACHI(payload.getDIACHI());
        customer1.setSDT(payload.getSDT());
        customer1.setEMAIL(payload.getEMAIL());
        this.save(customer1);

    }

    public void updateCustomerPass(String MAKH, UpdateCustomerDTO payload){
        Customer customer1 = this.findEntityById(MAKH);
        String pwd = payload.getPASSWORD();
        String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt(10));
        payload.setPASSWORD(hashedPwd);

        customer1.setPASSWORD(payload.getPASSWORD());
        this.save(customer1);

    }

    public List<CustomerDTO> customFindAll(){
        List<CustomerDTO> dtoList = new ArrayList<>();
        List<Customer> list = thisRepository.findAll();
        list.forEach(item -> {
            CustomerDTO customerDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(customerDTO);
        });
        return dtoList;
    }
}
