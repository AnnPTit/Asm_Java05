package com.example.demo.service;

import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SanPhamService {
    List<SanPham> getAll();

    boolean add(SanPham sanPham);

    Page<SanPham> getPaginatedProducts(Pageable pageable);

    SanPham findById(Long id);

    boolean remove(Long id);

    boolean update(SanPham sanPham);

    Page<SanPham> findSanPhamByTrangThai(Pageable pageable);

    SanPham getDetailSanPham(Long id);
}
