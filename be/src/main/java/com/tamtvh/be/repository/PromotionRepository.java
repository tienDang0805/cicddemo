package com.tamtvh.be.repository;

import com.tamtvh.be.model.Promotion;
import com.tamtvh.be.model.Provider;
import com.tamtvh.be.model.Staff;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends AbstractRepository<Promotion, String>{

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

    @Query(value = "select *\n" +
            "from khuyenmai k \n" +
            "order by k.MAKM DESC \n" +
            "LIMIT 1", nativeQuery = true
    )
    Promotion findPromotionMaxId();

    Promotion findByMAKM(String MAKM);

    @Query(value = "select *\n" +
            "from khuyenmai k \n" +
            "where k.NGAYBATDAU <= CURRENT_DATE()  and k.NGAYKETTHUC >= CURRENT_DATE() \n"
            , nativeQuery = true
    )
    Promotion getCurPromo();

    @Modifying
    @Query(value = "delete from banruou.khuyenmai where MAKM = :MAKM",
            nativeQuery = true
    )
    void deletePromotion(@Param("MAKM") String MAKM);
}
