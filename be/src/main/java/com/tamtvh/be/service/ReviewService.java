package com.tamtvh.be.service;

import com.tamtvh.be.create.CreateProviderDTO;
import com.tamtvh.be.create.CreateReviewDTO;
import com.tamtvh.be.dto.ReviewDTO;
import com.tamtvh.be.model.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService extends AbstractService<ReviewDTO, Review>{
    List<ReviewDTO> findByMadong(String MADONG);
    ResponseEntity<?> create(CreateReviewDTO payload);

    List<ReviewDTO> customFindAll();
}
