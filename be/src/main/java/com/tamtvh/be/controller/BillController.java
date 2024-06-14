package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateBillDTO;
import com.tamtvh.be.create.CreatePhieudatDTO;
import com.tamtvh.be.dto.BillDTO;
import com.tamtvh.be.dto.ChangePriceDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.mapper.BillMapper;
import com.tamtvh.be.mapper.ChangePriceMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Bill;
import com.tamtvh.be.model.ChangePrice;
import com.tamtvh.be.service.BillService;
import com.tamtvh.be.service.ChangePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/bill")
public class BillController extends AbstractController<BillService, BillMapper, BillDTO, Bill> {

    @Autowired
    BillService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public BillService getService() {
        initService();
        return service;
    }

    private BillMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public BillMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<BillDTO> getAllBill() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public BillDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }

    @GetMapping("/phieudat/{MAPD}")
    public BillDTO findByMAPD(@PathVariable("MAPD") String MAPD) {
        return thisService.findByMAPD(MAPD);
    }
    @Transactional
    @PostMapping("")
    public void create(@RequestBody CreateBillDTO payload){
        String MAHD = UUID.randomUUID().toString().substring(0,19);
        payload.setMAHD(MAHD);
        thisService.create(payload);
    }
    @Transactional
    @PutMapping("")
    public void update(@RequestBody CreateBillDTO payload){
        thisService.update(payload);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        thisService.delete(id);
    }
}
