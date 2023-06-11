package com.example.demo.repository;

import com.example.demo.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MauSacRepository extends JpaRepository<MauSac, Long> {
    MauSac findByMa(String ma);
    List<MauSac> findAllByTrangThai(Integer trangThai);

    @Query(value = "select *  from MauSac where trang_thai = ?1", nativeQuery = true)
    Page<MauSac> getAllByTrangThai(int status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update mausac set trang_thai = 0 where id = ?1", nativeQuery = true)
    int remove(Long id);
}
