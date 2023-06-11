package com.example.demo.service.impl;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public TaiKhoan checkLogin(String email, String pass) {
        try {
            return taiKhoanRepository.checkLogIn(email, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaiKhoan getTaiKhoanByMa(String ma) {
        try {
            return taiKhoanRepository.getTaiKhoanByMa(ma);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(TaiKhoan taiKhoan) {
        try {
            taiKhoanRepository.save(taiKhoan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TaiKhoan getTaiKhoanByUsername(String username) {
        try {
            return taiKhoanRepository.getTaiKhoanByUsername(username);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaiKhoan checkLogInWithUser(String username, String pass) {
        try {
            return taiKhoanRepository.checkLogInWithUser(username, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaiKhoan getTaiKhoanByUsernameAndEmail(String userNameOrEmail) {
        try {
            return taiKhoanRepository.getTaiKhoanByUsernameAndEmail(userNameOrEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updatePassById(Long id, String pass) {
        try {
            return taiKhoanRepository.updatePassById(id, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
