package com.example.demo.repository;

import com.example.demo.entity.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // danh dau day là 1 bean , @Repository cũng là 1 componet
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {
    DanhMuc findByMa(String ma);

    List<DanhMuc> findAllByTrangThai(Integer trangThai);

    @Query(value = "select *  from DanhMuc where trang_thai = ?1", nativeQuery = true)
    Page<DanhMuc> getAllByTrangThai(int status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update danhmuc set trang_thai = 0 where id = ?1", nativeQuery = true)
    int remove(Long id);
}
