package com.example.demo.service.impl;


import com.example.demo.entity.NhaSanXuat;
import com.example.demo.repository.NhaSanXuatRepository;
import com.example.demo.service.NhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhaSanXuatServiceImpl implements NhaSanXuatService {
    @Autowired
    private NhaSanXuatRepository nhaSanXuatRepository;

    @Override
    public List<NhaSanXuat> getAll() {
        return nhaSanXuatRepository.findAllByTrangThai(1);
    }

    @Override
    public Page<NhaSanXuat> getPaginatedProducts(Pageable pageable) {
        return nhaSanXuatRepository.getAllByTrangThai(1, pageable);
    }

    @Override
    public boolean add(NhaSanXuat mauSac) {
        try {
            if (nhaSanXuatRepository.findByMa(mauSac.getMa()) != null) {
                return false;
            }
            nhaSanXuatRepository.save(mauSac);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NhaSanXuat findById(Long id) {
        try {
            return nhaSanXuatRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remove(Long id) {
        try {
            nhaSanXuatRepository.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(NhaSanXuat mauSac) {
        try {
            nhaSanXuatRepository.save(mauSac);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
