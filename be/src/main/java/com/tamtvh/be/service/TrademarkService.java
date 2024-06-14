package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateTrademarkDTO;
import com.tamtvh.be.dto.TrademarkDTO;
import com.tamtvh.be.model.Trademark;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrademarkService extends AbstractService<TrademarkDTO, Trademark>{
    TrademarkDTO findByMATH(String MATH);
    ResponseEntity<?> create(CreateTrademarkDTO payload);
    ResponseEntity<?> update(String id, CreateTrademarkDTO payload);
    ResponseEntity<?> deleteTM(String id);

    List<TrademarkDTO> customFindAll();
}
