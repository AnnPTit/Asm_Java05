package com.example.demo.repository;

import com.example.demo.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    @Query(value = "select * from hoadonchitiet where id_hoadon = ?1 and trang_thai = ?2", nativeQuery = true)
    List<HoaDonChiTiet> findByIdHoaDonAndTrangThai(Long idHd, int trangThai);

    @Transactional
    @Modifying
    @Query(value = "UPDATE hoadonchitiet SET trang_thai = ?2 where id_hoadon = ?1", nativeQuery = true)
    int updateTTHDCTByIdHD(Long idHd, int trangThai);

    @Transactional
    @Modifying
    @Query(value = "UPDATE hoadonchitiet SET trang_thai = ?2 where id = ?1", nativeQuery = true)
    int updateTTHDCTById(Long id, int trangThai);

    @Query("SELECT hct FROM HoaDonChiTiet hct JOIN hct.hoaDon hd JOIN hd.taiKhoan tk " +
            "WHERE tk.id = ?1 AND hd.trangThai = ?2 and hct.trangThai =?3")
    List<HoaDonChiTiet> findHDCTByAccountIdAndTrangThai(Long accountId, int trangThai, int trangThaiHDCT);

    @Query(value = "SELECT hc.* FROM hoadonchitiet hc " +
            "JOIN hoadon hd ON hd.id = hc.id_hoadon " +
            "JOIN taikhoan tk ON tk.id = hd.id_taikhoan " +
            "WHERE tk.id = ?1 AND hc.trang_thai = 0 ", nativeQuery = true)
    List<HoaDonChiTiet> getProductCancelOfUser(Long idtk);


    @Query(value = "SELECT COUNT(*) AS so_hdct " +
            "FROM hoadon " +
            "JOIN hoadonchitiet ON hoadon.id = hoadonchitiet.id_hoadon " +
            "WHERE hoadon.id IN (" +
            "    SELECT hoadon.id " +
            "    FROM hoadon " +
            "    JOIN hoadonchitiet ON hoadon.id = hoadonchitiet.id_hoadon " +
            "    WHERE hoadonchitiet.id = ?1" +
            ") AND hoadonchitiet.trang_thai = 1", nativeQuery = true)
    int countHoaDonChiTietByHdctId(Long hdctId);
}



