package com.example.demo.service;


import com.example.demo.entity.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DanhMucService {
    List<DanhMuc> getAll();

    Page<DanhMuc> getPaginatedProducts(Pageable pageable);

    boolean add(DanhMuc mauSac);

    DanhMuc findById(Long id);

    boolean remove(Long id);

    boolean update(DanhMuc mauSac);
}
