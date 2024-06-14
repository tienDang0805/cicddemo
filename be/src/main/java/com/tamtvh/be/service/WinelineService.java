package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateWinelineDTO;
import com.tamtvh.be.dto.*;
import com.tamtvh.be.model.Wineline;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WinelineService extends AbstractService<WinelineDTO, Wineline>{
    WinelineDTO findById(String id);
    List<WinelineDTO> getHotProducts();
    List<WinelineDTO> getProductsOnType(String MALOAI);
    List<WinelineDTO> getProductByName(String name);
    List<WinelineDTO> getTopPromoProduct();
    ResponseEntity<?> create(CreateWinelineDTO payload);
    ResponseEntity<?> update(String id, CreateWinelineDTO payload);
    ResponseEntity<?> updateSLT(String id, Integer slt);

    ResponseEntity<?> updateSLTminus(String id, Integer slt);
    ResponseEntity<?> deleteWl(String id);
    List<WinelineDTO> getListProductByArr(String[] arr);
    List<DetailPaypal1> getDetailPaypal(DetailPaypal arr[]);
    getQuantityProd[] getQuantity(String id);

    boolean checkSlt(String MADONG, Integer SOLUONG);

    List<WinelineDTO> customFindAll();
}
