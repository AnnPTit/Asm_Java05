package com.example.demo.service;

import com.example.demo.entity.TaiKhoan;

public interface TaiKhoanService {
    TaiKhoan checkLogin(String email, String pass);

    TaiKhoan getTaiKhoanByMa(String ma);

    boolean add(TaiKhoan taiKhoan);

    TaiKhoan getTaiKhoanByUsername(String username);

    TaiKhoan checkLogInWithUser(String username, String pass);

    TaiKhoan getTaiKhoanByUsernameAndEmail(String userNameOrEmail);

    int updatePassById(Long id, String pass);
}
