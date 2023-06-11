package com.example.demo.repository;

import com.example.demo.entity.Hinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HinhRepository extends JpaRepository<Hinh, Long> {


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hinh WHERE id = ?1", nativeQuery = true)
    void deletePictureById(Long id);
}
