package com.example.demo.repository;

import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    SanPham findByMa(String ma);

    List<SanPham> findAllByTrangThai(Integer trangThai);

    @Query(value = "select *  from SanPham where trang_thai = ?1", nativeQuery = true)
    Page<SanPham> getAllByTrangThai(int status, Pageable pageable);


    @Query(value = "SELECT sanpham.ma,sanpham.ten, sanpham.id ,sanpham.trang_thai FROM sanpham JOIN chitietsp ON sanpham.id = chitietsp.id_product WHERE chitietsp.trang_thai = 1 GROUP BY sanpham.id", nativeQuery = true)
    Page<SanPham> findSanPhamByTrangThai(Pageable pageable);

    @Query(value = "select sanpham.ma,sanpham.ten, sanpham.id ,sanpham.trang_thai from sanpham  join chitietsp on sanpham.id = chitietsp.id_product where chitietsp.trang_thai =1  and sanpham.id = ?1", nativeQuery = true)
    SanPham getDetailSanPham(Long id);

    @Transactional
    @Modifying
    @Query(value = "update sanpham set trang_thai = 0 where id = ?1", nativeQuery = true)
    int remove(Long id);

}
