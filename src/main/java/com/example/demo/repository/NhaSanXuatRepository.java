package com.example.demo.repository;

import com.example.demo.entity.NhaSanXuat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NhaSanXuatRepository extends JpaRepository<NhaSanXuat, Long> {
    NhaSanXuat findByMa(String ma);

    List<NhaSanXuat> findAllByTrangThai(Integer trangThai);

    @Query(value = "select *  from NSX where trang_thai = ?1", nativeQuery = true)
    Page<NhaSanXuat> getAllByTrangThai(int status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update nsx set trang_thai = 0 where id = ?1", nativeQuery = true)
    int remove(Long id);
}
