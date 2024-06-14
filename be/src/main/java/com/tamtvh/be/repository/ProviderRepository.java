package com.tamtvh.be.repository;

import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Provider;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Trademark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends AbstractRepository<Provider, String>{

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

    Provider findByMANCC(String MANCC);

    @Query(value = "select *\n" +
            "from nhacungcap n \n" +
            "order by n.MANCC DESC \n" +
            "LIMIT 1", nativeQuery = true
    )
    Provider findProviderMaxId();
}
