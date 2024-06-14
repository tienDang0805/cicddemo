package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateCtPhieudatDTO;
import com.tamtvh.be.create.CreatePhieudatDTO;
import com.tamtvh.be.create.UpdatePhieudatDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.PhieudatMapper;
import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.PhieudatRepository;
import com.tamtvh.be.service.*;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class PhieudatServiceImpl extends AbstractServiceImpl<PhieudatRepository, PhieudatMapper, PhieudatDTO, Phieudat>
        implements PhieudatService {
    @Autowired
    PhieudatRepository thisRepository;

    @Autowired
    StaffService staffService;

    @Autowired
    CustomerService customerService;

    @Autowired
    WinelineService winelineService;

    @Autowired
    Ct_phieudatService ctPhieudatService;


    private PhieudatMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PhieudatRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public PhieudatMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public List<PhieudatDTO> findByState(String TRANGTHAI) {
        List<PhieudatDTO> listDTO = new ArrayList<>();
        if(TRANGTHAI.equals("ALL")){
            List<Phieudat> listEntity = thisRepository.findAllStateAll();
            listEntity.forEach(pd -> {
                var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
                listDTO.add(temp);

            });
            return listDTO;
        }

        List<Phieudat> listEntity = thisRepository.findByState(TRANGTHAI);
        listEntity.forEach(pd -> {
            var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
            listDTO.add(temp);
        });
        return listDTO;
    }

    public List<PhieudatDTO> findByStateAndNVGH(String TRANGTHAI, String MANV) {
        List<PhieudatDTO> listDTO = new ArrayList<>();
        if(TRANGTHAI.equals("ALL")){
            List<Phieudat> listEntity = thisRepository.findAllByMANVGHContaining1(MANV);
            listEntity.forEach(pd -> {
                var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
                listDTO.add(temp);

            });
            return listDTO;
        }

        List<Phieudat> listEntity = thisRepository.findAllByMANVGHContainingAndTRANGTHAIContaining1(MANV, TRANGTHAI);
        listEntity.forEach(pd -> {
            var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
            listDTO.add(temp);
        });
        return listDTO;
    }

    public PhieudatDTO findById(String id) {
        Phieudat pd  = thisRepository.findByMAPD(id);
        PhieudatDTO dto = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
        return dto;
    }

    public List<PhieudatDTO> getListPdFromTo(String from, String to){
        List<PhieudatDTO> listDTO = new ArrayList<>();
        List<Phieudat> listEntity = thisRepository.getListPdFromTo(from, to);
        listEntity.forEach(pd -> {
            var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
            listDTO.add(temp);

        });
        return  listDTO;
    }

    public List<PhieudatDTO> getListPdByNVGH(String MANV){
        List<PhieudatDTO> listDTO = new ArrayList<>();
        List<Phieudat> listEntity = thisRepository.getListPdByNVGH(MANV);
        listEntity.forEach(pd -> {
            var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
            listDTO.add(temp);

        });
        return  listDTO;
    }

    public List<PhieudatDTO> getListPdByCustomer(String MAKH){
        List<PhieudatDTO> listDTO = new ArrayList<>();
        List<Phieudat> listEntity = thisRepository.getListPdByCustomer(MAKH);
        listEntity.forEach(pd -> {
            var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
            listDTO.add(temp);

        });
        return  listDTO;
    }

    public List<PhieudatDTO> findByStateAndCustomer(String TRANGTHAI, String MAKH) {
        List<PhieudatDTO> listDTO = new ArrayList<>();
        if(TRANGTHAI.equals("ALL")){
            List<Phieudat> listEntity = thisRepository.getListPdByCustomer(MAKH);
            listEntity.forEach(pd -> {
                var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
                listDTO.add(temp);

            });
            return listDTO;
        }

        List<Phieudat> listEntity = thisRepository.getListPdByStateAndCustomer(TRANGTHAI, MAKH);
        listEntity.forEach(pd -> {
            var temp = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
            listDTO.add(temp);
        });
        return listDTO;
    }

    public void update(String id, UpdatePhieudatDTO payload){
        Phieudat phieudat = this.findEntityById(id);
        if (phieudat == null) throw new ObjectNotFoundException("PD is not exist",id);
        Staff staff = staffService.findByIdCustom(payload.getMANVD());
        phieudat.setMAPD(payload.getMAPD());
        phieudat.setNGAYDAT(payload.getNGAYDAT());
        phieudat.setHONN(payload.getHONN());
        phieudat.setTENNN(payload.getTENNN());
        phieudat.setDIACHINN(payload.getDIACHINN());
        phieudat.setSDTNN(payload.getSDTNN());
        phieudat.setGHICHU(payload.getGHICHU());
        phieudat.setTRANGTHAI(payload.getTRANGTHAI());
        phieudat.setStaff(staff);
        phieudat.setMANVGH(payload.getMANVGH());

        this.save(phieudat);

    }


    public void create(CreatePhieudatDTO payload) {
        List<CreateCtPhieudatDTO> CTPDS = payload.getCTPDS();
        CTPDS.forEach(item -> {
            if(winelineService.checkSlt(item.getMADONG(), item.getSOLUONG()) == false){
                throw new InternalError("Sản phẩm không đủ số lượng trong kho!");
            }
        });
        Phieudat phieudat = new Phieudat();
        phieudat.setMAPD(payload.getMAPD());
        phieudat.setNGAYDAT(payload.getNGAYDAT());
        phieudat.setHONN(payload.getHONN());
        phieudat.setTENNN(payload.getTENNN());
        phieudat.setDIACHINN(payload.getDIACHINN());
        phieudat.setSDTNN(payload.getSDTNN());
        phieudat.setGHICHU(payload.getGHICHU());
        phieudat.setTRANGTHAI(payload.getTRANGTHAI());
        phieudat.setMANVGH(payload.getMANVGH());
        Customer customer = customerService.findEntityById(payload.getMAKH());
        phieudat.setCustomer(customer);
//        Staff staff = staffService.findEntityById(payload.getMANVD());
//        phieudat.setStaff(staff);
        this.save(phieudat);

        CTPDS.forEach(item -> {
            item.setMAPD(payload.getMAPD());
            winelineService.updateSLT(item.getMADONG(), item.getSOLUONG());
            ctPhieudatService.create(item);
        });
    }


    public void createPaypalPd(CreatePhieudatDTO payload) {
        List<CreateCtPhieudatDTO> CTPDS = payload.getCTPDS();
        CTPDS.forEach(item -> {
            if(winelineService.checkSlt(item.getMADONG(), item.getSOLUONG()) == false){
                throw new InternalError("Sản phẩm không đủ số lượng trong kho!");
            }
        });
        Phieudat phieudat = new Phieudat();
        phieudat.setMAPD(payload.getMAPD());
        phieudat.setNGAYDAT(payload.getNGAYDAT());
        phieudat.setHONN(payload.getHONN());
        phieudat.setTENNN(payload.getTENNN());
        phieudat.setDIACHINN(payload.getDIACHINN());
        phieudat.setSDTNN(payload.getSDTNN());
        phieudat.setGHICHU(payload.getGHICHU());
        phieudat.setTRANGTHAI(payload.getTRANGTHAI());
        phieudat.setMANVGH(payload.getMANVGH());
        Customer customer = customerService.findEntityById(payload.getMAKH());
        phieudat.setCustomer(customer);
//        Staff staff = staffService.findEntityById(payload.getMANVD());
//        phieudat.setStaff(staff);
        this.save(phieudat);

        CTPDS.forEach(item -> {
            item.setMAPD(payload.getMAPD());
            winelineService.updateSLT(item.getMADONG(), item.getSOLUONG());
            ctPhieudatService.create(item);
        });
    }


    public void checkSltPayPal(CreatePhieudatDTO payload){
        List<CreateCtPhieudatDTO> CTPDS = payload.getCTPDS();
        CTPDS.forEach(item -> {
            if(winelineService.checkSlt(item.getMADONG(), item.getSOLUONG()) == false){
                throw new InternalError("Sản phẩm không đủ số lượng trong kho!");
            }
        });
    }

    public List<PhieudatDTO> customFindAll(){
        List<PhieudatDTO> dtoList = new ArrayList<>();
        List<Phieudat> list = thisRepository.findAll();
        list.forEach(item -> {
            PhieudatDTO phieudatDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(phieudatDTO);
        });
        return dtoList;
    }

}
