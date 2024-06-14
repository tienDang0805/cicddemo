package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateCtPromoDTO;
import com.tamtvh.be.dto.Ct_promotionDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.model.Ct_promotion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Ct_promotionService extends AbstractService<Ct_promotionDTO, Ct_promotion>{
    void create(CreateCtPromoDTO payload);
    ResponseEntity<?> deleteCtp(String MAKM, String MADONG);

    List<Ct_promotionDTO> customFindAll();
}
