package com.example.demo.service.impl;

import com.example.demo.entity.DanhMuc;

import com.example.demo.repository.DanhMucRepository;
import com.example.demo.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucServiceImpl implements DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;


    @Override
    public List<DanhMuc> getAll() {
        return danhMucRepository.findAllByTrangThai(1);
    }

    @Override
    public Page<DanhMuc> getPaginatedProducts(Pageable pageable) {
        return danhMucRepository.getAllByTrangThai(1, pageable);
    }

    @Override
    public boolean add(DanhMuc danhMuc) {
        try {
            if (danhMucRepository.findByMa(danhMuc.getMa()) != null) {
                return false;
            }
            danhMucRepository.save(danhMuc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DanhMuc findById(Long id) {
        try {
            return danhMucRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remove(Long id) {
        try {
            danhMucRepository.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(DanhMuc danhMuc) {
        try {
            danhMucRepository.save(danhMuc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
