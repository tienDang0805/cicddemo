package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreatePromoDTO;
import com.tamtvh.be.dto.PromotionDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.PromotionMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Promotion;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.PromotionRepository;
import com.tamtvh.be.service.PromotionService;
import com.tamtvh.be.service.StaffService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class PromotionServiceImpl extends AbstractServiceImpl<PromotionRepository, PromotionMapper, PromotionDTO, Promotion>
        implements PromotionService {
    @Autowired
    PromotionRepository thisRepository;

    @Autowired
    StaffService staffService;

    private PromotionMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PromotionRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public PromotionMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public ResponseEntity<?> create(CreatePromoDTO payload){
        Promotion promotion = new Promotion();
        var staff = staffService.findEntityById(payload.getMANV());
        Promotion last = thisRepository.findPromotionMaxId();
        var id = Integer.parseInt(last.getMAKM()) + 1;

        promotion.setMAKM(String.format("%03d", id));
        promotion.setTENKM(payload.getTENKM());
        promotion.setStaff(staff);
        promotion.setLIDO(payload.getLIDO());
        promotion.setNGAYBATDAU(payload.getNGAYBATDAU());
        promotion.setNGAYKETTHUC(payload.getNGAYKETTHUC());
        PromotionDTO response = new PromotionDTO();
        response = getMapper().toDtoWithoutLists1(promotion, new CycleAvoidingMappingContext());
        this.save(promotion);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public PromotionDTO findById(String id) {
        Promotion promotion  = thisRepository.findByMAKM(id);
        PromotionDTO dto = getMapper().toDtoWithoutLists(promotion, new CycleAvoidingMappingContext());
        return dto;
    }

    public PromotionDTO getCurrentPromo(){
        Promotion promotion = thisRepository.getCurPromo();
        PromotionDTO dto = getMapper().toDtoWithoutLists(promotion, new CycleAvoidingMappingContext());
        return dto;
    }

    public ResponseEntity<?> update(String id, CreatePromoDTO payload){
        Promotion promotion = this.findEntityById(id);
        if(promotion == null){
            throw new ObjectNotFoundException("Promotion is not exist", id);
        }
        promotion.setTENKM(payload.getTENKM());
        promotion.setNGAYBATDAU(payload.getNGAYBATDAU());
        promotion.setNGAYKETTHUC(payload.getNGAYKETTHUC());
        promotion.setLIDO(payload.getLIDO());

        PromotionDTO response = new PromotionDTO();
        response = getMapper().toDtoWithoutLists1(promotion, new CycleAvoidingMappingContext());
        this.save(promotion);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> deletePrm(String MAKM){
        Promotion promotion = this.findEntityById(MAKM);
        if(promotion == null){
            throw new ObjectNotFoundException("Promo is not exist", MAKM);
        }
        if(!promotion.getCt_promotions().isEmpty() ){
            throw new ObjectNotFoundException("Ct-promo exist, cannot delete", MAKM);
        }
        getRepository().deletePromotion(promotion.getMAKM());
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", MAKM));
    }

    public List<PromotionDTO> customFindAll(){
        List<PromotionDTO> dtoList = new ArrayList<>();
        List<Promotion> list = thisRepository.findAll();
        list.forEach(item -> {
            PromotionDTO promotionDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(promotionDTO);
        });
        return dtoList;
    }
}
