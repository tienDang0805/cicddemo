package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateBillDTO;
import com.tamtvh.be.dto.BillDTO;
import com.tamtvh.be.model.Bill;

import java.util.List;

public interface BillService extends AbstractService<BillDTO, Bill>{
    BillDTO findById(String MAHD);
    BillDTO findByMAPD(String MAPD);

    void create(CreateBillDTO payload);

    void update(CreateBillDTO payload);

    List<BillDTO> customFindAll();
}
