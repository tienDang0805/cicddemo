package com.tamtvh.be.repository;

import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhieudatRepository extends AbstractRepository<Phieudat, String>{

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
            "from phieudat pd \n" +
            "where pd.TRANGTHAI = :TRANGTHAI " +
            "order by pd.NGAYDAT DESC", nativeQuery = true
    )
    List<Phieudat> findByState(@Param("TRANGTHAI") String TRANGTHAI);

    //List<Phieudat> findAllByMANVGHContainingAndTRANGTHAIContaining(String MANVGH, String TRANGTHAI);
    @Query(value = "select * \n" +
            "from phieudat p \n" +
            "where p.MANVGH = :MANVGH\n" +
            "and p.TRANGTHAI = :TRANGTHAI \n" +
            "order by p.NGAYDAT DESC " , nativeQuery = true
    )
    List<Phieudat> findAllByMANVGHContainingAndTRANGTHAIContaining1(String MANVGH, String TRANGTHAI);

    //List<Phieudat> findAllByMANVGHContaining(String MANVGH);
    @Query(value = "select * \n" +
            "from phieudat p \n" +
            "where p.MANVGH = :MANVGH\n" +
            "order by p.NGAYDAT DESC " , nativeQuery = true
    )
    List<Phieudat> findAllByMANVGHContaining1(String MANVGH);

    Phieudat findByMAPD(String MAPD);

    @Query(value = "select * \n" +
            "from phieudat p \n" +
            "where p.NGAYDAT >= :from " +
            "and p.NGAYDAT <= :to " +
            "and p.TRANGTHAI = 'Đã giao' ", nativeQuery = true
    )
    List<Phieudat> getListPdFromTo(String from, String to);

    @Query(value = "select * \n" +
            "from phieudat p \n" +
            "where p.MANVGH = :MANV " +
            "order by p.NGAYDAT DESC " , nativeQuery = true
    )
    List<Phieudat> getListPdByNVGH(String MANV);

    @Query(value = "select * \n" +
            "from phieudat p \n" +
            "where p.MAKH = :MAKH " +
            "order by p.NGAYDAT DESC " , nativeQuery = true
    )
    List<Phieudat> getListPdByCustomer(String MAKH);

    @Query(value = "select * \n" +
            "from phieudat p \n" +
            "where p.MAKH = :MAKH " +
            "and p.TRANGTHAI = :TRANGTHAI " +
            "order by p.NGAYDAT DESC " , nativeQuery = true
    )
    List<Phieudat> getListPdByStateAndCustomer(String TRANGTHAI, String MAKH);

    @Query(value = "select * \n" +
            "from phieudat p \n" +
            "order by p.NGAYDAT DESC " , nativeQuery = true
    )
    List<Phieudat> findAllStateAll();



}
