package com.example.demo.service.impl;

import com.example.demo.entity.GioHang;
import com.example.demo.repository.GioHangRepository;
import com.example.demo.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GioHangServiceImpl implements GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public boolean add(GioHang gioHang) {
        try {
            gioHangRepository.save(gioHang);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(GioHang gioHang) {
        try {
            gioHangRepository.save(gioHang);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public GioHang getGHByCtspAndTk(Long idctsp, Long idtk) {
        try {
            return gioHangRepository.getGHByCtspAndTk(idctsp, idtk);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int countByTaiKhoan(Long idTaikhoan) {
        return gioHangRepository.countByTaiKhoan(idTaikhoan);
    }

    @Override
    public List<GioHang> getGhOfUser(Long idtk) {
        List<GioHang> list = new ArrayList<>();
        try {
            list = gioHangRepository.getGhOfUser(idtk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean remove(Long id) {
        try {
            gioHangRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public GioHang getById(Long id) {
        return gioHangRepository.findById(id).orElse(null);
    }

    @Override
    public int updateTrangThaiGHById(Long id, int trangThai) {
        try {
            return gioHangRepository.updateTrangThaiGHById(id, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
