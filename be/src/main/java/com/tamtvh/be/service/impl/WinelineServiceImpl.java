package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateChangpriceDTO;
import com.tamtvh.be.create.CreateWinelineDTO;
import com.tamtvh.be.dto.*;
import com.tamtvh.be.mapper.WinelineMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.*;
import com.tamtvh.be.repository.WinelineRepository;
import com.tamtvh.be.service.*;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ServiceHelper
public class WinelineServiceImpl extends AbstractServiceImpl<WinelineRepository, WinelineMapper, WinelineDTO, Wineline>
        implements WinelineService {
    @Autowired
    WinelineRepository thisRepository;

    @Autowired
    WinetypeService winetypeService;

    @Autowired
    TrademarkService trademarkService;

    @Autowired
    ChangePriceService changePriceService;

    private WinelineMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public WinelineRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public WinelineMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public WinelineDTO findById(String id) {
        Wineline wineline  = thisRepository.findByMADONG(id);
        WinelineDTO dto = getMapper().toDtoWithoutLists(wineline, new CycleAvoidingMappingContext());
        return dto;
    }

    public List<WinelineDTO> getHotProducts(){
        List<WinelineDTO> listDTO = new ArrayList<>();
        List<Wineline> listEntity = thisRepository.getHotProds();
        listEntity.forEach(wineline -> {
            var temp = getMapper().toDtoWithoutLists(wineline, new CycleAvoidingMappingContext());
            listDTO.add(temp);
        });
        return listDTO;
    }

    public List<WinelineDTO> getProductsOnType(String MALOAI){
        List<WinelineDTO> listDTO = new ArrayList<>();
        List<Wineline> listEntity = thisRepository.findByMALOAI(MALOAI);
        listEntity.forEach(wineline -> {
            var temp = getMapper().toDtoWithoutLists(wineline, new CycleAvoidingMappingContext());
            listDTO.add(temp);
        });
        return listDTO;
    }

    public List<WinelineDTO> getProductByName(String name){
        List<WinelineDTO> listDTO = new ArrayList<>();
        List<Wineline> listEntity = thisRepository.getProdByName(name);
        listEntity.forEach(wineline -> {
            var temp = getMapper().toDtoWithoutLists(wineline, new CycleAvoidingMappingContext());
            listDTO.add(temp);
        });
        return listDTO;
    }

    public List<WinelineDTO> getTopPromoProduct(){
        List<WinelineDTO> listDTO = new ArrayList<>();
        List<Wineline> listEntity = thisRepository.getTopPromoProd();
        listEntity.forEach(wineline -> {
            var temp = getMapper().toDtoWithoutLists(wineline, new CycleAvoidingMappingContext());
            listDTO.add(temp);
        });
        return listDTO;
    }

    public ResponseEntity<?> create(CreateWinelineDTO payload){
        Wineline last = thisRepository.findWinelineMaxId();
        var id = Integer.parseInt(last.getMADONG()) + 1;
        Winetype winetype = winetypeService.findEntityById(payload.getMALOAI());
        Trademark trademark = trademarkService.findEntityById(payload.getMATH());
        Wineline wineline = new Wineline();
        wineline.setMADONG(String.format("%03d", id));
        wineline.setTENDONG(payload.getTENDONG());
        wineline.setTRANGTHAI(payload.getTRANGTHAI());
        wineline.setHINHANH(payload.getHINHANH());
        wineline.setMOTA(payload.getMOTA());
        wineline.setCHITIET(payload.getCHITIET());
        wineline.setSOLUONGTON(payload.getSOLUONGTON());
        wineline.setTrademark(trademark);
        wineline.setWinetype(winetype);

        WinelineDTO response = new WinelineDTO();
        response = getMapper().toDtoWithoutLists1(wineline, new CycleAvoidingMappingContext());
        this.save(wineline);

        Date date = new Date();

        CreateChangpriceDTO changePrice = new CreateChangpriceDTO();
        changePrice.setMADONG(wineline.getMADONG());
        changePrice.setGIA(payload.getGIA());
        changePrice.setMANV(payload.getMANV());
        changePrice.setNGAYTHAYDOI(date);

        changePriceService.create(changePrice);

        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> update(String id, CreateWinelineDTO payload){
        Wineline wineline = this.findEntityById(id);
        if(wineline == null){
            throw new ObjectNotFoundException("Provider is not exist", id);
        }
        Winetype winetype = winetypeService.findEntityById(payload.getMALOAI());
        Trademark trademark = trademarkService.findEntityById(payload.getMATH());
        wineline.setTENDONG(payload.getTENDONG());
        wineline.setHINHANH(payload.getHINHANH());
        wineline.setTRANGTHAI(payload.getTRANGTHAI());
        wineline.setMOTA(payload.getMOTA());
        wineline.setCHITIET(payload.getCHITIET());
        wineline.setSOLUONGTON(payload.getSOLUONGTON());
        wineline.setWinetype(winetype);
        wineline.setTrademark(trademark);

        WinelineDTO response = new WinelineDTO();
        response = getMapper().toDtoWithoutLists1(wineline, new CycleAvoidingMappingContext());
        this.save(wineline);

        Date date = new Date();

        CreateChangpriceDTO changePrice = new CreateChangpriceDTO();
        changePrice.setMADONG(wineline.getMADONG());
        changePrice.setGIA(payload.getGIA());
        changePrice.setMANV(payload.getMANV());
        changePrice.setNGAYTHAYDOI(date);

        changePriceService.create(changePrice);

        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> updateSLT(String id, Integer slt){
        Wineline wineline = this.findEntityById(id);
        if(wineline == null){
            throw new ObjectNotFoundException("Provider is not exist", id);
        }
        wineline.setSOLUONGTON(wineline.getSOLUONGTON() - slt);
        WinelineDTO response = new WinelineDTO();
        response = getMapper().toDtoWithoutLists1(wineline, new CycleAvoidingMappingContext());
        this.save(wineline);

        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> updateSLTminus(String id, Integer slt){
        Wineline wineline = this.findEntityById(id);
        if(wineline == null){
            throw new ObjectNotFoundException("Provider is not exist", id);
        }
        wineline.setSOLUONGTON(wineline.getSOLUONGTON() + slt);
        WinelineDTO response = new WinelineDTO();
        response = getMapper().toDtoWithoutLists1(wineline, new CycleAvoidingMappingContext());
        this.save(wineline);

        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public ResponseEntity<?> deleteWl(String id){
        Wineline wineline = thisRepository.customFindWinelineById(id);
        if(wineline == null){
            throw new ObjectNotFoundException("Provider is not exist", id);
        }
        if(wineline.getCt_phieudats().isEmpty() == false || wineline.getReviews().isEmpty() == false){
            throw new ObjectNotFoundException("Cannot delete", id);
        }
        getRepository().deleteWineline(id);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", id));
    }

    public List<WinelineDTO> getListProductByArr(String[] arr){
        List<WinelineDTO> listDTO = new ArrayList<>();

        for(String id: arr ) {
            Wineline wineline = thisRepository.customFindWinelineById(id);
            WinelineDTO dto = getMapper().toDtoWithoutLists(wineline, new CycleAvoidingMappingContext());
            listDTO.add(dto);
        }
        return listDTO;
    }

    public List<DetailPaypal1> getDetailPaypal(DetailPaypal[] arr){
        List<DetailPaypal1> list = new ArrayList<>();
        for(DetailPaypal dpp : arr){
            Wineline product = thisRepository.customFindWinelineById(dpp.getProductId());
            Float price = this.checkPrice(product.getChangeprices());

            Float promoPrice = checkPrice(product.getChangeprices()) * this.convertKm(product.getCt_promotions())/100;
            Integer quantityCur = dpp.getQuantity();
            DetailPaypal1 detailPaypal = new DetailPaypal1();
            WinelineDTO winelineDTO = new WinelineDTO();
            winelineDTO = getMapper().toDtoWithoutLists(product, new CycleAvoidingMappingContext());
            detailPaypal.setProduct(winelineDTO);
            detailPaypal.setPrice(price - promoPrice);
            detailPaypal.setQuantity(quantityCur);
            list.add(detailPaypal);
        }
        return list;
    }

    public float checkPrice(Collection<ChangePrice> list){
        ChangePrice[] arr = new ChangePrice[list.size()];
        list.toArray(arr);
        Date NGAY = arr[0].getNGAYTHAYDOI();
        Float GIA = arr[0].getGIA();
        for(ChangePrice cPrice : arr){
            if(cPrice.getNGAYTHAYDOI().after(NGAY)){
                NGAY = cPrice.getNGAYTHAYDOI();
                GIA = cPrice.getGIA();
            }
        }
        return GIA;
    }

    public Integer convertKm(Collection<Ct_promotion> listKm){
        Ct_promotion[] arr = new Ct_promotion[listKm.size()];
        listKm.toArray(arr);
        if(arr[0].getPromotion().getNGAYBATDAU().before(new Date()) && arr[0].getPromotion().getNGAYKETTHUC().after(new Date())  ){
            if(listKm.isEmpty() || arr[0] == null  || arr[0].getPHANTRAMGIAM() == null){
                return 0;
            }
            else {
                return arr[0].getPHANTRAMGIAM();
            }
        }else{
            return 0;
        }

    }

    public String checkKm(Collection<Ct_promotion> listKm){
        return this.convertKm(listKm) + "%";
    }


    public getQuantityProd[] getQuantity(String id){
        List<getQuantityProd> list = new ArrayList<>();
        Wineline wineline = thisRepository.customFindWinelineById(id);
        getQuantityProd quantity = new getQuantityProd();
        quantity.setSoluongton(wineline.getSOLUONGTON());
        list.add(quantity);
        return list.toArray(new getQuantityProd[0]);
    }

    public boolean checkSlt(String MADONG, Integer SOLUONG) {
        Wineline wineline = this.findEntityById(MADONG);
        if(wineline.getSOLUONGTON() < SOLUONG){
            return false;
        }
        return true;
    }

    public List<WinelineDTO> customFindAll(){
        List<WinelineDTO> dtoList = new ArrayList<>();
        List<Wineline> list = thisRepository.findAll();
        list.forEach(item -> {
            WinelineDTO winelineDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(winelineDTO);
        });
        return dtoList;
    }
}
