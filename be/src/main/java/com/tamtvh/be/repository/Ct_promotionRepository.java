package com.tamtvh.be.repository;

import com.tamtvh.be.model.Ct_phieutra;
import com.tamtvh.be.model.Ct_promotion;
import com.tamtvh.be.model.Staff;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.TransactionScoped;
import java.util.List;
import java.util.Optional;

public interface Ct_promotionRepository extends AbstractRepository<Ct_promotion, String>{

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
            "from ct_khuyenmai c \n" +
            "where c.MAKM = :MAKM " +
            "and c.MADONG = :MADONG ", nativeQuery = true
    )
    Ct_promotion findOneCtp(@Param("MAKM") String MAKM, @Param("MADONG") String MADONG);


    @Modifying
    @Query(value = "DELETE FROM ct_khuyenmai WHERE MAKM = :MAKM AND MADONG = :MADONG", nativeQuery = true
    )
    void customDeleteById(@Param("MAKM") String MAKM, @Param("MADONG") String MADONG);
}
