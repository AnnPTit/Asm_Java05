package com.example.demo.service.impl;

import com.example.demo.entity.Hinh;
import com.example.demo.repository.HinhRepository;
import com.example.demo.service.HinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HinhServiceImpl implements HinhService {
    @Autowired
    private HinhRepository hinhRepository;

    @Override
    public boolean remove(Long id) {
        try {
            System.out.println("Thanh cong " + id);
            hinhRepository.deletePictureById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Hinh getHinhById(Long id) {
        return hinhRepository.findById(id).orElse(null);
    }
}
