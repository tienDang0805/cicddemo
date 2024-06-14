package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.dto.ChangePriceDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.ChangePriceMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.ChangePrice;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.ChangePriceRepository;
import com.tamtvh.be.service.ChangePriceService;
import com.tamtvh.be.service.StaffService;
import com.tamtvh.be.service.WinelineService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class ChangePriceServiceImpl extends AbstractServiceImpl<ChangePriceRepository, ChangePriceMapper, ChangePriceDTO, ChangePrice>
        implements ChangePriceService {
    @Autowired
    ChangePriceRepository thisRepository;

    private WinelineService winelineService;
    @Autowired
    public ChangePriceServiceImpl(@Lazy WinelineService winelineService){
        this.winelineService = winelineService;
    }



    @Autowired
    StaffService staffService;

    private ChangePriceMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ChangePriceRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ChangePriceMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public ResponseEntity<?> create(CreateChangpriceDTO payload){
        ChangePrice changePrice = new ChangePrice();
        var wineline = this.winelineService.findEntityById(payload.getMADONG());
        var staff = staffService.findEntityById(payload.getMANV());
        changePrice.setWineline(wineline);
        changePrice.setStaff(staff);
        changePrice.setGIA(payload.getGIA());
        changePrice.setNGAYTHAYDOI(payload.getNGAYTHAYDOI());
        ChangePriceDTO response = new ChangePriceDTO();
        response = getMapper().toDtoWithoutLists1(changePrice, new CycleAvoidingMappingContext());
        this.save(changePrice);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public List<ChangePriceDTO> customFindAll(){
        List<ChangePriceDTO> dtoList = new ArrayList<>();
        List<ChangePrice> list = thisRepository.findAll();
        list.forEach(item -> {
            ChangePriceDTO changePriceDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(changePriceDTO);
        });
        return dtoList;
    }
}
