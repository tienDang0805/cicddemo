package com.tamtvh.be.service.impl;

import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateReviewDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.dto.ReviewDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.ProviderMapper;
import com.tamtvh.be.mapper.ReviewMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.*;
import com.tamtvh.be.repository.ProviderRepository;
import com.tamtvh.be.repository.ReviewRepository;
import com.tamtvh.be.service.CustomerService;
import com.tamtvh.be.service.ProviderService;
import com.tamtvh.be.service.ReviewService;
import com.tamtvh.be.service.WinelineService;
import com.tamtvh.be.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class ReviewServiceImpl extends AbstractServiceImpl<ReviewRepository, ReviewMapper, ReviewDTO, Review>
        implements ReviewService {
    @Autowired
    ReviewRepository thisRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    WinelineService winelineService;

    private ReviewMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ReviewRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ReviewMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    public List<ReviewDTO> findByMadong(String MADONG) {
        List<Review> reviews  = thisRepository.getReviewByMADONG(MADONG);
        List<ReviewDTO> listDto = new ArrayList<>();
        reviews.forEach(review -> {
            var temp = getMapper().toDtoWithoutLists(review, new CycleAvoidingMappingContext());
            listDto.add(temp);

        });
        return listDto;
    }

    public ResponseEntity<?> create(CreateReviewDTO payload){
        Review review = new Review();
        Customer customer = customerService.findEntityById(payload.getMAKH());
        Wineline wineline = winelineService.findEntityById(payload.getMADONG());
        review.setNGAYDANHGIA(payload.getNGAYDANHGIA());
        review.setNOIDUNG(payload.getNOIDUNG());
        review.setRATING(payload.getRATING());
        review.setCustomer(customer);
        review.setWineline(wineline);
        ReviewDTO response = new ReviewDTO();
        response = getMapper().toDtoWithoutLists1(review, new CycleAvoidingMappingContext());
        this.save(review);
        return ResponseEntity.ok(new CustomResponse(200, "Resquest done", response));
    }

    public List<ReviewDTO> customFindAll(){
        List<ReviewDTO> dtoList = new ArrayList<>();
        List<Review> list = thisRepository.findAll();
        list.forEach(item -> {
            ReviewDTO reviewDTO = getMapper().toDtoWithoutLists(item, new CycleAvoidingMappingContext());
            dtoList.add(reviewDTO);
        });
        return dtoList;
    }
}
