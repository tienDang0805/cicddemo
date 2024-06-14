package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.create.CreateTrademarkDTO;
import com.tamtvh.be.dto.ChangePriceDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.TrademarkDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.TrademarkMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.*;
import com.tamtvh.be.repository.TrademarkRepository;
import com.tamtvh.be.service.TrademarkService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class TrademarkServiceImpl extends AbstractServiceImpl<TrademarkRepository, TrademarkMapper, TrademarkDTO, Trademark>
        implements TrademarkService {
    @Autowired
    TrademarkRepository thisRepository;

    private TrademarkMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public TrademarkRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public TrademarkMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public TrademarkDTO findByMATH(String MATH){
        Trademark trademark  = thisRepository.findByMATH(MATH);
        TrademarkDTO dto = getMapper().toDtoWithoutLists(trademark, new CycleAvoidingMappingContext());
        return dto;
    }

    public ResponseEntity<?> create(CreateTrademarkDTO payload){
        Trademark last = thisRepository.findTrademarkMaxId();
        var id = Integer.parseInt(last.getMATH()) + 1;
        Trademark trademark = new Trademark();
        trademark.setMATH(String.format("%03d", id));
        trademark.setTENTH(payload.getTENTH());
        TrademarkDTO response = new TrademarkDTO();
        response = getMapper().toDtoWithoutLists1(trademark, new CycleAvoidingMappingContext());
        this.save(trademark);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> update(String id, CreateTrademarkDTO payload){
        Trademark trademark = this.findEntityById(id);
        if(trademark == null){
            throw new ObjectNotFoundException("Brand is not exist", id);
        }
        trademark.setTENTH(payload.getTENTH());
        TrademarkDTO response = new TrademarkDTO();
        response = getMapper().toDtoWithoutLists1(trademark, new CycleAvoidingMappingContext());
        this.save(trademark);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> deleteTM(String id){
        Trademark trademark = this.findEntityById(id);
        if(trademark == null){
            throw new ObjectNotFoundException("Brand is not exist", id);
        }
        if(trademark.getWinelines().isEmpty() == false){
            throw new ObjectNotFoundException("Wineline exist, cannot delete", id);
        }
        this.delete(trademark.getMATH());
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", id));
    }

    public List<TrademarkDTO> customFindAll(){
        List<TrademarkDTO> dtoList = new ArrayList<>();
        List<Trademark> list = thisRepository.findAll();
        list.forEach(item -> {
            TrademarkDTO trademarkDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(trademarkDTO);
        });
        return dtoList;
    }
}
