package com.tamtvh.be.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.tamtvh.be.create.CreatePhieudatDTO;
import com.tamtvh.be.create.UpdatePhieudatDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.mapper.PhieudatMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.service.PhieudatService;
import com.tamtvh.be.service.WinelineService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/phieudat")
public class PhieudatController extends AbstractController<PhieudatService, PhieudatMapper, PhieudatDTO, Phieudat> {

    @Autowired
    PhieudatService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public PhieudatService getService() {
        initService();
        return service;
    }

    @Autowired
    WinelineService winelineService;

    private PhieudatMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public PhieudatMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<PhieudatDTO> getAllPd() {
        return thisService.customFindAll();
    }

    @GetMapping("/{id}")
    public PhieudatDTO findById(@PathVariable String id) {
        return thisService.findById(id);
    }

    @GetMapping("/state/{state}")
    public List<PhieudatDTO> findByState(@PathVariable("state") String state) {
        return thisService.findByState(state);
    }

    @GetMapping("/NV&state/{state},{id}")
    public List<PhieudatDTO> findByStateAndNVGH(@PathVariable("state") String state, @PathVariable("id") String id) {
        return thisService.findByStateAndNVGH(state, id);
    }
    @Transactional
    @PostMapping("")
    public void create(@RequestBody CreatePhieudatDTO payload){
        String MAPD = UUID.randomUUID().toString().substring(0,19);
        payload.setMAPD(MAPD);
        thisService.create(payload);
    }

    @Transactional
    @PostMapping("/create/paypal")
    public void createPaypalPd(@RequestBody CreatePhieudatDTO payload){
        thisService.createPaypalPd(payload);
    }

    @Transactional
    @PostMapping("/create/checkslt/paypal")
    public void checkPaypalPd(@RequestBody CreatePhieudatDTO payload){
        thisService.checkSltPayPal(payload);
    }


    @Transactional
    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody UpdatePhieudatDTO body){
        thisService.update(id,body);
    }

//    @PostMapping("/total")
//    public void getTotal(@RequestBody)

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        thisService.delete(id);
    }

    @GetMapping("/rev/list/{from},{to}")
    public List<PhieudatDTO> getPdFromTo(@PathVariable("from") String from, @PathVariable("to") String to) {
        return thisService.getListPdFromTo(from, to);
    }


    @GetMapping("/list/NVGH/{id}")
    public List<PhieudatDTO> getPdByNVGH(@PathVariable("id") String MANV){
        return thisService.getListPdByNVGH(MANV);
    }

    @GetMapping("/list/pd/KH/{id}")
    public List<PhieudatDTO> getPdByCustomer(@PathVariable("id") String MANV){
        return thisService.getListPdByCustomer(MANV);
    }

    @GetMapping("/cus&state/list/{state},{id}")
    public List<PhieudatDTO> findByStateAndCustomer(@PathVariable("state") String TRANGTHAI, @PathVariable("id") String MAKH){
        String tt = "'".concat(TRANGTHAI).concat("'");;
        return thisService.findByStateAndCustomer(TRANGTHAI, MAKH);
    }
}
