package com.example.demo.service.impl;

import com.example.demo.entity.MauSac;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAllByTrangThai(1);
    }

    @Override
    public Page<MauSac> getPaginatedProducts(Pageable pageable) {
        return mauSacRepository.getAllByTrangThai(1,pageable);
    }

    @Override
    public boolean add(MauSac mauSac) {
        try {
            if (mauSacRepository.findByMa(mauSac.getMa()) != null) {
                return false;
            }
            mauSacRepository.save(mauSac);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MauSac findById(Long id) {
        try {
            return mauSacRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remove(Long id) {
        try {
            mauSacRepository.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(MauSac mauSac) {
        try {
            mauSacRepository.save(mauSac);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
