package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateWinelineDTO;
import com.tamtvh.be.dto.*;
import com.tamtvh.be.mapper.WinelineMapper;
import com.tamtvh.be.model.Wineline;
import com.tamtvh.be.service.WinelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/winelines")
public class WinelineController extends AbstractController<WinelineService, WinelineMapper, WinelineDTO, Wineline> {

    @Autowired
    WinelineService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public WinelineService getService() {
        initService();
        return service;
    }

    private WinelineMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public WinelineMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<WinelineDTO> getAllWineline() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public WinelineDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }

    @GetMapping("/product/hot")
    public List<WinelineDTO> getHotProducts() {
        return thisService.getHotProducts();
    }

    @GetMapping("/product/{MALOAI}")
    public List<WinelineDTO> getProductsOnType(@PathVariable("MALOAI") String MALOAI) {
        return thisService.getProductsOnType(MALOAI);
    }

    @GetMapping("/product/name/{name}")
    public List<WinelineDTO> getProductByName(@PathVariable("name") String name) {
        return thisService.getProductByName(name);
    }

    @GetMapping("/product/promo/top")
    public List<WinelineDTO> getTopPromoProduct() {
        return thisService.getTopPromoProduct();
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateWinelineDTO createWinelineDTO){
        return thisService.create(createWinelineDTO);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody CreateWinelineDTO updateWinelineDTO){
        return thisService.update(id ,updateWinelineDTO);
    }

    @Transactional
    @PutMapping("/update/slt/{id},{slt}")
    public ResponseEntity<?> updateSLT(@PathVariable("id") String id, @PathVariable("slt") Integer slt){
        return thisService.updateSLT(id ,slt);
    }

    @Transactional
    @PutMapping("/update/slt/minus/{id},{slt}")
    public ResponseEntity<?> updateSLTminus(@PathVariable("id") String id, @PathVariable("slt") Integer slt){
        return thisService.updateSLTminus(id ,slt);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return thisService.deleteWl(id);
    }

    @PostMapping("/product/arr")
    public List<WinelineDTO> getListProductByArr(@RequestBody String[] arr){
        return thisService.getListProductByArr(arr);
    }

    @PostMapping("/product/paypal/arr")
    public List<DetailPaypal1> getDetailPaypal(@RequestBody DetailPaypal[] arr){
        return thisService.getDetailPaypal(arr);
    }

    @GetMapping("/product/quantity/{id}")
    public getQuantityProd[] getQuantity(@PathVariable("id") String id) {
        return thisService.getQuantity(id);
    }
}
