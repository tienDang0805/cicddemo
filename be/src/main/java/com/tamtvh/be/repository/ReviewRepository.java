package com.tamtvh.be.repository;

import com.tamtvh.be.model.Provider;
import com.tamtvh.be.model.Review;
import com.tamtvh.be.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends AbstractRepository<Review, String>{

    @Query(value = "select * " +
            "from nhanvien k " +
            "where month(now()) - month(k.created_at) < :n_month " , nativeQuery = true)
    List<Staff> findUserRegisterInMonthCurrent(@Param("n_month") Integer n_month);

    @Query(value = "select count(k.MANV) " +
            "from nhanvien k " +
            "where month(k.created_at) = month (now()) " +
            "and year (k.created_at) = year (now()) ", nativeQuery = true)
    Long countUserRegistrationInThisMonth();

    @Query(value = "select count(k.MANV) \n" +
            "from nhanvien k \n" +
            "where month(k.created_at) = :month " +
            "and year (k.created_at) = year (now()) ", nativeQuery = true
    )
    Long countUserRegistrationInOneMonth(@Param("month") Integer month);

    @Query(value = "select * \n" +
            "from danhgia d \n" +
            "where d.MADONG = :MADONG " , nativeQuery = true
    )
    List<Review> getReviewByMADONG(@Param("MADONG") String MADONG);
}
