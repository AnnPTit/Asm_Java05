package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    HoaDon findByMaHD(String ma);

    HoaDon findByIdAndTrangThai(Long id, int trangThai);

    Page<HoaDon> findByTrangThai(Integer trangThai, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE hoadon SET trang_thai = 2 WHERE id = ?1", nativeQuery = true)
    int xacNhanDonHangByMa(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE hoadon SET trang_thai = ?2 WHERE id = ?1", nativeQuery = true)
    int updateTrangThaiHoaDonById(Long id, int trangThai);

    @Query("SELECT hd FROM HoaDon hd JOIN hd.hoaDonChiTietList hdct JOIN hd.taiKhoan tk " +
            "WHERE tk.id = ?1 AND hd.trangThai = ?2")
    List<HoaDon> findHDByAccountIdAndTrangThai(Long accountId, int trangThai);
}
