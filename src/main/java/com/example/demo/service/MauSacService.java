package com.example.demo.service;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MauSacService {
    List<MauSac> getAll();

    Page<MauSac> getPaginatedProducts(Pageable pageable);

    boolean add(MauSac mauSac);

    MauSac findById(Long id );

    boolean remove(Long id);

    Boolean update(MauSac mauSac);
}
