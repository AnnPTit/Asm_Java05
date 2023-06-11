package com.example.demo.service.impl;

import com.example.demo.entity.SanPham;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    private List<SanPham> list = new ArrayList<>();
    @Autowired
    SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        list = sanPhamRepository.findAllByTrangThai(1);
        return list;
    }

    @Override
    public boolean add(SanPham sanPham) {
        SanPham sanPham1 = sanPhamRepository.findByMa(sanPham.getMa());
        if (sanPham1 != null) {
            return false;
        }
        sanPhamRepository.save(sanPham);
        return true;
    }

    @Override
    public Page<SanPham> getPaginatedProducts(Pageable pageable) {
        return sanPhamRepository.getAllByTrangThai(1, pageable);
    }

    @Override
    public SanPham findById(Long id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public boolean remove(Long id) {
        try {
            sanPhamRepository.remove(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(SanPham sanPham) {
        try {
            sanPhamRepository.save(sanPham);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<SanPham> findSanPhamByTrangThai(Pageable pageable) {
        return sanPhamRepository.findSanPhamByTrangThai(pageable);
    }

    @Override
    public SanPham getDetailSanPham(Long id) {
        try {
            return sanPhamRepository.getDetailSanPham(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
