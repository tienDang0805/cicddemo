package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateBillDTO;
import com.tamtvh.be.dto.BillDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.BillMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Bill;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.BillRepository;
import com.tamtvh.be.service.BillService;
import com.tamtvh.be.service.PhieudatService;
import com.tamtvh.be.service.StaffService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class BillServiceImpl extends AbstractServiceImpl<BillRepository, BillMapper, BillDTO, Bill>
        implements BillService {
    @Autowired
    BillRepository thisRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    PhieudatService phieudatService;

    private BillMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public BillRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public BillMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Override
    public BillDTO findById(String MAHD) {
        Bill bill  = thisRepository.findByMAHD(MAHD);
        BillDTO dto = getMapper().toDtoWithoutLists(bill, new CycleAvoidingMappingContext());
        return dto;
    }

    public BillDTO findByMAPD(String MAPD){
        Bill bill = thisRepository.customFindByMAPD(MAPD);
        BillDTO dto = getMapper().toDtoWithoutLists(bill, new CycleAvoidingMappingContext());
        return dto;
    }

    public void create (CreateBillDTO payload){
        Bill bill = new Bill();
        Staff staff = staffService.findEntityById(payload.getMANV());
        Phieudat phieudat = phieudatService.findEntityById(payload.getMAPD());
        bill.setMAHD(payload.getMAHD());
        bill.setMASOTHUE(payload.getMASOTHUE());
        bill.setNGAY(payload.getNGAY());
        bill.setTHANHTIEN(payload.getTHANHTIEN());
        bill.setStaff(staff);
        bill.setPhieudat(phieudat);
        this.save(bill);
    }

    public void update (CreateBillDTO payload){
        Bill bill = this.findEntityById(payload.getMAHD());
        if (bill == null) throw new ObjectNotFoundException("PD is not exist",payload.getMAHD());

        Staff staff = staffService.findEntityById(payload.getMANV());
        Phieudat phieudat = phieudatService.findEntityById(payload.getMAPD());
        bill.setMAHD(payload.getMAHD());
        bill.setMASOTHUE(payload.getMASOTHUE());
        bill.setNGAY(payload.getNGAY());
        bill.setTHANHTIEN(payload.getTHANHTIEN());
        bill.setStaff(staff);
        bill.setPhieudat(phieudat);
        this.save(bill);
    }

    public List<BillDTO> customFindAll(){
        List<BillDTO> dtoList = new ArrayList<>();
        List<Bill> list = thisRepository.findAll();
        list.forEach(item -> {
            BillDTO billDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(billDTO);
        });
        return dtoList;
    }

}
