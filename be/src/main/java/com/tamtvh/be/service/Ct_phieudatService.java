package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateCtPhieudatDTO;
import com.tamtvh.be.create.CtpdFromTo;
import com.tamtvh.be.create.TotalRev;
import com.tamtvh.be.dto.Ct_phieudatDTO;
import com.tamtvh.be.model.Ct_phieudat;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Ct_phieudatService extends AbstractService<Ct_phieudatDTO, Ct_phieudat>{
    List<CtpdFromTo> getRevenueProduct(String from, String to);
    List<TotalRev> getTotalRev(String from, String to);

    void create(CreateCtPhieudatDTO payload);

    List<Ct_phieudatDTO> customFindAll();
}
