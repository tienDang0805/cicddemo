package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateTrademarkDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.model.Provider;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProviderService extends AbstractService<ProviderDTO, Provider>{
    ProviderDTO findById(String id);
    ResponseEntity<?> create(CreateProviderDTO payload);
    ResponseEntity<?> update(String id, CreateProviderDTO payload);
    ResponseEntity<?> deletePvd(String id);

    List<ProviderDTO> customFindAll();
}
