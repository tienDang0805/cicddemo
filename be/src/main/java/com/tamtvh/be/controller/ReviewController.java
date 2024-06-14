package com.tamtvh.be.controller;

import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateReviewDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.dto.ReviewDTO;
import com.tamtvh.be.mapper.ReviewMapper;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.message.response.CustomResponse;
import com.tamtvh.be.model.Review;
import com.tamtvh.be.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/review")
public class ReviewController extends AbstractController<ReviewService, ReviewMapper, ReviewDTO, Review> {

    @Autowired
    ReviewService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ReviewService getService() {
        initService();
        return service;
    }

    private ReviewMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ReviewMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("")
    public List<ReviewDTO> getAllReview() {
        return thisService.customFindAll();
    }

    @GetMapping("/madong/{madong}")
    public List<ReviewDTO> findById(@PathVariable("madong") String MADONG) {
        return thisService.findByMadong(MADONG);
    }
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateReviewDTO createReviewDTO){
        return thisService.create(createReviewDTO);
    }
}
