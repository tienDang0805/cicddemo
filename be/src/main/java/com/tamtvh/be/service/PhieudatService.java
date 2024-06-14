package com.tamtvh.be.service;

import com.tamtvh.be.create.CreatePhieudatDTO;
import com.tamtvh.be.create.UpdatePhieudatDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.model.Phieudat;

import java.util.List;

public interface PhieudatService extends AbstractService<PhieudatDTO, Phieudat>{

    List<PhieudatDTO> findByState(String TRANGTHAI);

    List<PhieudatDTO> findByStateAndNVGH(String TRANGTHAI, String MANV);

    PhieudatDTO findById(String MAPD);

    List<PhieudatDTO> getListPdFromTo(String from, String to);

    List<PhieudatDTO> getListPdByNVGH(String MANV);

    List<PhieudatDTO> getListPdByCustomer(String MAKH);

    List<PhieudatDTO> findByStateAndCustomer(String TRANGTHAI, String MAKH);

    void update(String id, UpdatePhieudatDTO payload);

    void create(CreatePhieudatDTO payload);

    void createPaypalPd(CreatePhieudatDTO payload);

    void checkSltPayPal(CreatePhieudatDTO payload);

    List<PhieudatDTO> customFindAll();

}
