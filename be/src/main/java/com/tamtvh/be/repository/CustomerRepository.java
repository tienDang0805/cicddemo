package com.tamtvh.be.repository;

import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Wineline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends AbstractRepository<Customer, String>{

    @Query(value = "select * " +
            "from khachhang k " +
            "where month(now()) - month(k.created_at) < :n_month " , nativeQuery = true)
    List<Customer> findUserRegisterInMonthCurrent(@Param("n_month") Integer n_month);

    @Query(value = "select count(k.MAKH) " +
            "from khachhang k " +
            "where month(k.created_at) = month (now()) " +
            "and year (k.created_at) = year (now()) ", nativeQuery = true)
    Long countUserRegistrationInThisMonth();

    @Query(value = "select count(k.MAKH) \n" +
            "from khachhang k \n" +
            "where month(k.created_at) = :month " +
            "and year (k.created_at) = year (now()) ", nativeQuery = true
    )
    Long countUserRegistrationInOneMonth(@Param("month") Integer month);

    Customer findByMAKH(String MAKH);

    Customer findByUSERNAME(String USERNAME);

    @Query(value = "select *\n" +
            "from khachhang d \n" +
            "order by d.MAKH DESC \n" +
            "LIMIT 1", nativeQuery = true
    )
    Customer findCustomerMaxId();
}
