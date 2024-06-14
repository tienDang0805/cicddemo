package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.StaffMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.StaffRepository;
import com.tamtvh.be.service.StaffService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@ServiceHelper
public class StaffServiceImpl extends AbstractServiceImpl<StaffRepository, StaffMapper, StaffDTO, Staff>
        implements StaffService {
    @Autowired
    StaffRepository thisRepository;

    private StaffMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public StaffRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public StaffMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public Staff findByUsername(String USERNAME){
        Staff staff = thisRepository.findByUSERNAME(USERNAME);
        return staff;
    }

    public StaffDTO findById(String id) {
        Staff staff  = thisRepository.findByMANV(id);
        StaffDTO dto = getMapper().toDtoWithoutLists(staff, new CycleAvoidingMappingContext());
        return dto;
    }

    public List<StaffDTO> getNVGH(){
        List<Staff> staffList = thisRepository.getNVGH();
        List<StaffDTO> staffDTOList = new ArrayList<StaffDTO>();

        staffList.forEach(staff -> {
            StaffDTO staffDTO = getMapper().toDtoWithoutLists(staff, new CycleAvoidingMappingContext());
            staffDTOList.add(staffDTO);
        });

        return staffDTOList;
    }

    public Staff findByIdCustom(String MANV){
        Staff staff = thisRepository.findByMANV(MANV);
        return staff;
    }

    public List<StaffDTO> customFindAll(){
        List<StaffDTO> dtoList = new ArrayList<>();
        List<Staff> list = thisRepository.findAll();
        list.forEach(item -> {
            StaffDTO staffDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(staffDTO);
        });
        return dtoList;
    }
}
