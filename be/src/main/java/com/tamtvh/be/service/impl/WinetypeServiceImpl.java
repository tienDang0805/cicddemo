package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateWinetypeDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.WinetypeMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Provider;
import com.tamtvh.be.model.Wineline;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.WinetypeRepository;
import com.tamtvh.be.service.WinetypeService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class WinetypeServiceImpl extends AbstractServiceImpl<WinetypeRepository, WinetypeMapper, WinetypeDTO, Winetype>
        implements WinetypeService {
    @Autowired
    WinetypeRepository thisRepository;

    private WinetypeMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public WinetypeRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public WinetypeMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public WinetypeDTO findById(String id) {
        Winetype winetype  = thisRepository.findByMALOAI(id);
        WinetypeDTO dto = getMapper().toDtoWithoutLists(winetype, new CycleAvoidingMappingContext());
        return dto;
    }

    public ResponseEntity<?> create(CreateWinetypeDTO payload){
        Winetype last = thisRepository.findWinetypeMaxId();
        var id = Integer.parseInt(last.getMALOAI()) + 1;
        Winetype winetype = new Winetype();
        winetype.setMALOAI(String.format("%03d", id));
        winetype.setTENLOAI(payload.getTENLOAI());
        WinetypeDTO response = new WinetypeDTO();
        response = getMapper().toDtoWithoutLists1(winetype, new CycleAvoidingMappingContext());
        this.save(winetype);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> update(String id, CreateWinetypeDTO payload){
        Winetype winetype = this.findEntityById(id);
        if(winetype == null){
            throw new ObjectNotFoundException("Winetype is not exist", id);
        }
        winetype.setTENLOAI(payload.getTENLOAI());

        WinetypeDTO response = new WinetypeDTO();
        response = getMapper().toDtoWithoutLists1(winetype, new CycleAvoidingMappingContext());
        this.save(winetype);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> deleteWt(String id){
        Winetype winetype = this.findEntityById(id);
        if(winetype == null){
            throw new ObjectNotFoundException("Winetype is not exist", id);
        }
        if(winetype.getWinelines().isEmpty() == false ){
            throw new ObjectNotFoundException("Winelines exist, cannot delete", id);
        }
        this.delete(winetype.getMALOAI());
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", id));
    }

    public List<WinetypeDTO> customFindAll(){
        List<WinetypeDTO> dtoList = new ArrayList<>();
        List<Winetype> list = thisRepository.findAll();
        list.forEach(item -> {
            WinetypeDTO winetypeDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(winetypeDTO);
        });
        return dtoList;
    }
}
