package com.tamtvh.be.service;

import com.tamtvh.be.dto.CungcapDTO;
import com.tamtvh.be.model.Cungcap;

import java.util.List;

public interface CungcapService extends AbstractService<CungcapDTO, Cungcap>{
    List<CungcapDTO> customFindAll();
}
