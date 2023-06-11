package com.example.demo.repository;

import com.example.demo.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    @Query(value = "select * from taikhoan where email = ?1 and matkhau = ?2 and trang_thai = 1 ", nativeQuery = true)
    TaiKhoan checkLogIn(String email, String pass);

    @Query(value = "select * from taikhoan where username = ?1 and matkhau = ?2 and trang_thai = 1 ", nativeQuery = true)
    TaiKhoan checkLogInWithUser(String username, String pass);

    @Query(value = "select * from taikhoan where ma = ?1 and trang_thai = 1 ", nativeQuery = true)
    TaiKhoan getTaiKhoanByMa(String ma);

    @Query(value = "select * from taikhoan where username = ?1 and trang_thai = 1 ", nativeQuery = true)
    TaiKhoan getTaiKhoanByUsername(String username);

    @Query(value = "select * from taikhoan where (username = ?1 or email=?1) and trang_thai = 1 ", nativeQuery = true)
    TaiKhoan getTaiKhoanByUsernameAndEmail(String userNameOrEmail);

    @Transactional
    @Modifying
    @Query(value = "update taikhoan set matkhau = ?2 where id = ?1", nativeQuery = true)
    int updatePassById(Long id, String pass);

}
