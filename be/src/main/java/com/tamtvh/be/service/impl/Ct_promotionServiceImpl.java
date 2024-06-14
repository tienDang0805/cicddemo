package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateCtPromoDTO;
import com.tamtvh.be.dto.Ct_promotionDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.Ct_promotionMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Ct_promotion;
import com.tamtvh.be.model.Promotion;
import com.tamtvh.be.model.Wineline;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.model.key.Ct_promotionId;
import com.tamtvh.be.repository.Ct_promotionRepository;
import com.tamtvh.be.service.Ct_promotionService;
import com.tamtvh.be.service.PromotionService;
import com.tamtvh.be.service.WinelineService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class Ct_promotionServiceImpl extends AbstractServiceImpl<Ct_promotionRepository, Ct_promotionMapper, Ct_promotionDTO, Ct_promotion>
        implements Ct_promotionService {
    @Autowired
    Ct_promotionRepository thisRepository;

    @Autowired
    PromotionService promotionService;

    @Autowired
    WinelineService winelineService;

    private Ct_promotionMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_promotionRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public Ct_promotionMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public void create(CreateCtPromoDTO payload){
        Ct_promotion ctPromotion = new Ct_promotion();
        Promotion promotion = promotionService.findEntityById(payload.getMAKM());
        Wineline wineline = winelineService.findEntityById(payload.getMADONG());
        ctPromotion.setPromotion(promotion);
        ctPromotion.setWineline(wineline);
        ctPromotion.setPHANTRAMGIAM(payload.getPHANTRAMGIAM());
        this.save(ctPromotion);

    }

    public ResponseEntity<?> deleteCtp(String MAKM, String MADONG){

        getRepository().customDeleteById(MAKM, MADONG);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", MAKM));
    }

    public Ct_promotion findOne(String MAKM, String MADONG) {
        Ct_promotion ctPromotion = thisRepository.findOneCtp(MAKM, MADONG);
        return  ctPromotion;
    }

    public List<Ct_promotionDTO> customFindAll(){
        List<Ct_promotionDTO> dtoList = new ArrayList<>();
        List<Ct_promotion> list = thisRepository.findAll();
        list.forEach(item -> {
            Ct_promotionDTO ctPromotionDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(ctPromotionDTO);
        });
        return dtoList;
    }
}
