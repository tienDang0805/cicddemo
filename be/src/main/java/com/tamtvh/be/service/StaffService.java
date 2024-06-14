package com.tamtvh.be.service;

import com.tamtvh.be.dto.CustomerDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Staff;

import java.util.List;

public interface StaffService extends AbstractService<StaffDTO, Staff>{
    StaffDTO findById(String MANV);
    List<StaffDTO> getNVGH();

    Staff findByUsername(String USERNAME);

    Staff findByIdCustom(String MANV);

    List<StaffDTO> customFindAll();
}
