package com.example.demo.service.impl;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    private List<ChiTietSanPham> list;
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        list = chiTietSanPhamRepository.findAll();
        return list;
    }

    @Override
    public ChiTietSanPham getChiTietSanPhamById(Long id) {
        ChiTietSanPham chiTietSp = chiTietSanPhamRepository.getCtspById(id);
        return chiTietSp;
    }

    @Override
    public Page<ChiTietSanPham> getPaginatedProducts(Pageable pageable) {
        return chiTietSanPhamRepository.getAllByTrangThai(1, pageable);
    }

    @Override
    public boolean add(ChiTietSanPham chiTietSp) {
        try {
            chiTietSanPhamRepository.save(chiTietSp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(ChiTietSanPham chiTietSp) {
        try {
            chiTietSanPhamRepository.save(chiTietSp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(Long id) {
        try {
            chiTietSanPhamRepository.removeChiTietSp(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ChiTietSanPham> getListCtspByIdSp(Long id) {
        try {
            return chiTietSanPhamRepository.getListCtspByIdSp(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
