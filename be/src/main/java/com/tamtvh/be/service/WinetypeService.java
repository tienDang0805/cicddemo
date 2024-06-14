package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateWinetypeDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.model.Winetype;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WinetypeService extends AbstractService<WinetypeDTO, Winetype>{
    WinetypeDTO findById(String id);
    ResponseEntity<?> create(CreateWinetypeDTO payload);
    ResponseEntity<?> update(String id, CreateWinetypeDTO payload);
    ResponseEntity<?> deleteWt(String id);

    List<WinetypeDTO> customFindAll();
}
