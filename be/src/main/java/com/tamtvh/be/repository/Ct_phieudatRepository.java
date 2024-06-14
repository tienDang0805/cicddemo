package com.tamtvh.be.repository;

import com.tamtvh.be.create.CtpdFromTo;
import com.tamtvh.be.model.Ct_phieudat;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Ct_phieudatRepository extends AbstractRepository<Ct_phieudat, String>{

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

    @Query(value = "SELECT sum(cp.SOLUONG) as so_luong_ban, cp.MADONG ,  d.TENDONG , d.HINHANH \n" +
            "    FROM ct_phieudat cp\n" +
            "    INNER JOIN (\n" +
            "      SELECT * \n" +
            "      FROM phieudat\n" +
            "      WHERE NGAYDAT >= " +
            ":from" + " AND NGAYDAT <= " + ":to" +"\n" +
            "    ) p ON cp.MAPD = p.MAPD\n" +
            "    INNER JOIN dongruou d on cp.MADONG = d.MADONG \n" +
            "    GROUP BY cp.MADONG\n" +
            "    ORDER BY so_luong_ban DESC " , nativeQuery = true
    )
    List<Object[]> getRevenueProduct(@Param("from") String from, @Param("to") String to);

    @Query(value = "SELECT sum(cp.GIA * cp.SOLUONG) as tong, MONTH (p.NGAYDAT) as thang, YEAR(p.NGAYDAT) as nam\n" +
            "    FROM ct_phieudat cp\n" +
            "    INNER JOIN (\n" +
            "      SELECT * \n" +
            "      FROM phieudat p\n" +
            "      WHERE p.NGAYDAT >= :from AND p.NGAYDAT <= :to AND p.TRANGTHAI = 'Đã giao'\n" +
            "    ) p ON cp.MAPD = p.MAPD\n" +
            "    GROUP BY nam,thang\n" +
            "    ORDER BY nam,thang " , nativeQuery = true
    )
    List<Object[]> getTotalRev(@Param("from") String from, @Param("to") String to);


}
