package com.example.demo.repository;

import com.example.demo.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {

    @Query(value = "SELECT gh FROM GioHang gh WHERE gh.chiTietSp.id = ?1 AND gh.taiKhoan.id = ?2 AND gh.trangThai = 1")
    GioHang getGHByCtspAndTk(Long idCtsp, Long idTaikhoan);

    @Query(value = "SELECT COUNT(gh.chiTietSp.id) FROM GioHang gh WHERE gh.taiKhoan.id = ?1 AND gh.trangThai = 1")
    int countByTaiKhoan(Long idTaikhoan);

    @Query(value = "select * from giohang where id_taikhoan = ?1 and trang_thai = 1 ", nativeQuery = true)
    List<GioHang> getGhOfUser(Long idtk);

    @Transactional
    @Modifying
    @Query(value = "update giohang set trang_thai = ?2 where id = ?1", nativeQuery = true)
    int updateTrangThaiGHById(Long id, int trangThai);

}
