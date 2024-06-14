package com.tamtvh.be.service;

import com.tamtvh.be.create.CreatePromoDTO;
import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.dto.PromotionDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.model.Promotion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromotionService extends AbstractService<PromotionDTO, Promotion>{
    ResponseEntity<?> create(CreatePromoDTO payload);
    PromotionDTO findById(String id);
    PromotionDTO getCurrentPromo();
    ResponseEntity<?> update(String id, CreatePromoDTO payload);
    ResponseEntity<?> deletePrm(String MAKM);

    List<PromotionDTO> customFindAll();
}
