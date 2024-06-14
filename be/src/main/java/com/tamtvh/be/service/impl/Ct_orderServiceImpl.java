package com.tamtvh.be.service.impl;

import com.tamtvh.be.dto.Ct_orderDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.Ct_orderMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Ct_order;
import com.tamtvh.be.model.Winetype;
import com.tamtvh.be.repository.Ct_orderRepository;
import com.tamtvh.be.service.Ct_orderService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class Ct_orderServiceImpl extends AbstractServiceImpl<Ct_orderRepository, Ct_orderMapper, Ct_orderDTO, Ct_order>
        implements Ct_orderService {
    @Autowired
    Ct_orderRepository thisRepository;

    private Ct_orderMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public Ct_orderRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public Ct_orderMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public List<Ct_orderDTO> customFindAll(){
        List<Ct_orderDTO> dtoList = new ArrayList<>();
        List<Ct_order> list = thisRepository.findAll();
        list.forEach(item -> {
            Ct_orderDTO ctOrderDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(ctOrderDTO);
        });
        return dtoList;
    }
}
