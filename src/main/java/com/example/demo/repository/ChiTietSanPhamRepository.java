package com.example.demo.repository;

import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {
    @Query(value = "select *  from ChiTietSp where trang_thai = ?1", nativeQuery = true)
    Page<ChiTietSanPham> getAllByTrangThai(int status, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ChiTietSp set trang_thai = 0 where id = ?1", nativeQuery = true)
    void removeChiTietSp(Long id);

    @Query(value = "SELECT chitietsp.* FROM chitietsp JOIN sanpham ON sanpham.id = chitietsp.id_product WHERE chitietsp.trang_thai = 1 AND sanpham.id = ?1", nativeQuery = true)
    List<ChiTietSanPham> getListCtspByIdSp(Long id);

    @Query(value = "select * from chitietsp where id = ?1 and trang_thai =1 ", nativeQuery = true)
    ChiTietSanPham getCtspById(Long id);
}
