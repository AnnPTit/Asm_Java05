package com.example.demo.service;

import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> getAll();

    ChiTietSanPham getChiTietSanPhamById(Long id);

    Page<ChiTietSanPham> getPaginatedProducts(Pageable pageable);

    boolean add(ChiTietSanPham chiTietSp);

    boolean update(ChiTietSanPham chiTietSp);

    boolean remove(Long id);

    List<ChiTietSanPham> getListCtspByIdSp(Long id);

}
