package com.tamtvh.be.repository;

import com.tamtvh.be.dto.getQuantityProd;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Wineline;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WinelineRepository extends AbstractRepository<Wineline, String>{

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

    Wineline findByMADONG(String MADONG);

    @Query(value = "SELECT sum(cp.SOLUONG) as so_luong_ban, cp.MADONG ,  d.*\n" +
            "        FROM ct_phieudat cp\n" +
            "        INNER JOIN (\n" +
            "            SELECT *\n" +
            "            FROM phieudat p\n" +
            "            WHERE p.NGAYDAT >= (curdate() - INTERVAL 180 DAY)\n" +
            "        ) p ON cp.MAPD = p.MAPD\n" +
            "        INNER JOIN dongruou d on cp.MADONG = d.MADONG and d.SOLUONGTON > 0\n" +
            "        GROUP BY cp.MADONG\n" +
            "        ORDER BY so_luong_ban DESC LIMIT 5", nativeQuery = true
    )
    List<Wineline> getHotProds();

    @Query(value = "select * \n" +
            "from dongruou d \n" +
            "where d.MALOAI = :MALOAI "
            , nativeQuery = true
    )
    List<Wineline> findByMALOAI(String MALOAI);

    @Query(value = "SELECT d.*\n" +
            "from dongruou d \n" +
            "WHERE INSTR(d.TENDONG, :name)"
            , nativeQuery = true
    )
    List<Wineline> getProdByName(String name);

    @Query(value = "SELECT k.TENKM , k.NGAYBATDAU , k.NGAYKETTHUC , ck.PHANTRAMGIAM, d.*  \n" +
            "        FROM khuyenmai k \n" +
            "        inner join ct_khuyenmai ck \n" +
            "        on ck.MAKM = k.MAKM \n" +
            "        and ck.PHANTRAMGIAM > 30\n" +
            "        INNER join dongruou d \n" +
            "        on d.MADONG = ck.MADONG \n" +
            "        WHERE k.NGAYBATDAU <= CURRENT_DATE()  and k.NGAYKETTHUC >= CURRENT_DATE()"
            , nativeQuery = true
    )
    List<Wineline> getTopPromoProd();

    @Query(value = "select *\n" +
            "from dongruou d \n" +
            "order by d.MADONG DESC \n" +
            "LIMIT 1", nativeQuery = true
    )
    Wineline findWinelineMaxId();

    @Query(value = "select *\n" +
            "from dongruou d \n" +
            "where d.MADONG = :MADONG \n"
            , nativeQuery = true
    )
    Wineline customFindWinelineById(String MADONG);

    @Query(value = "SELECT soluongton from dongruou d \n" +
            "        WHERE d.MADONG = :MADONG"
            , nativeQuery = true
    )
    getQuantityProd findQuantity(String MADONG);

    @Modifying
    @Query(value = "delete from banruou.dongruou where MADONG = :MADONG",
        nativeQuery = true
    )
    void deleteWineline(@Param("MADONG") String MADONG);
}
