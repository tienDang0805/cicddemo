package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.dto.ChangePriceDTO;
import com.tamtvh.be.model.ChangePrice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChangePriceService extends AbstractService<ChangePriceDTO, ChangePrice>{
    ResponseEntity<?> create(CreateChangpriceDTO payload);

    List<ChangePriceDTO> customFindAll();
}
