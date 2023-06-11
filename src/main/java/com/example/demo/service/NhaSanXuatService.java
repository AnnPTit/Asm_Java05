package com.example.demo.service;


import com.example.demo.entity.NhaSanXuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface NhaSanXuatService {
    List<NhaSanXuat> getAll();

    Page<NhaSanXuat> getPaginatedProducts(Pageable pageable);

    boolean add(NhaSanXuat nsx);

    NhaSanXuat findById(Long id);

    boolean remove(Long id);

    boolean update(NhaSanXuat nsx);
}
