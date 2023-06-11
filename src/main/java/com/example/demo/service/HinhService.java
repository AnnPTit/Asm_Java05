package com.example.demo.service;

import com.example.demo.entity.Hinh;

public interface HinhService {
    boolean remove(Long id);

    Hinh getHinhById(Long id);
}
