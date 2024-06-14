package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateTrademarkDTO;
import com.tamtvh.be.dto.*;
import com.tamtvh.be.mapper.ProviderMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.*;
import com.tamtvh.be.repository.ProviderRepository;
import com.tamtvh.be.service.ProviderService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class ProviderServiceImpl extends AbstractServiceImpl<ProviderRepository, ProviderMapper, ProviderDTO, Provider>
        implements ProviderService {
    @Autowired
    ProviderRepository thisRepository;

    private ProviderMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ProviderRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ProviderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public ProviderDTO findById(String id) {
        Provider pd  = thisRepository.findByMANCC(id);
        ProviderDTO dto = getMapper().toDtoWithoutLists(pd, new CycleAvoidingMappingContext());
        return dto;
    }

    public ResponseEntity<?> create(CreateProviderDTO payload){
        Provider last = thisRepository.findProviderMaxId();
        var id = Integer.parseInt(last.getMANCC()) + 1;
        Provider provider = new Provider();
        provider.setMANCC(String.format("%03d", id));
        provider.setTENNCC(payload.getTENNCC());
        provider.setDIACHI(payload.getDIACHI());
        provider.setEMAIL(payload.getEMAIL());
        provider.setSDT(payload.getSDT());
        ProviderDTO response = new ProviderDTO();
        response = getMapper().toDtoWithoutLists1(provider, new CycleAvoidingMappingContext());
        this.save(provider);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> update(String id, CreateProviderDTO payload){
        Provider provider = this.findEntityById(id);
        if(provider == null){
            throw new ObjectNotFoundException("Provider is not exist", id);
        }
        provider.setTENNCC(payload.getTENNCC());
        provider.setDIACHI(payload.getDIACHI());
        provider.setEMAIL(payload.getEMAIL());
        provider.setSDT(payload.getSDT());
        ProviderDTO response = new ProviderDTO();
        response = getMapper().toDtoWithoutLists1(provider, new CycleAvoidingMappingContext());
        this.save(provider);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> deletePvd(String id){
        Provider provider = this.findEntityById(id);
        if(provider == null){
            throw new ObjectNotFoundException("Provider is not exist", id);
        }
        if(provider.getOrders().isEmpty() == false || provider.getCungcaps().isEmpty() == false){
            throw new ObjectNotFoundException("Order or Provide exist, cannot delete", id);
        }
        this.delete(provider.getMANCC());
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", id));
    }

    public List<ProviderDTO> customFindAll(){
        List<ProviderDTO> dtoList = new ArrayList<>();
        List<Provider> list = thisRepository.findAll();
        list.forEach(item -> {
            ProviderDTO providerDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(providerDTO);
        });
        return dtoList;
    }
}
