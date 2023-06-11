package com.example.demo.service;

import com.example.demo.entity.GioHang;

import java.util.List;

public interface GioHangService {
    boolean add(GioHang gioHang);

    boolean update(GioHang gioHang);

    GioHang getGHByCtspAndTk(Long idCtsp, Long idTk);

    int countByTaiKhoan(Long idTaikhoan);

    List<GioHang> getGhOfUser(Long idtk);

    boolean remove(Long id);

    GioHang getById(Long id);

    int updateTrangThaiGHById(Long id, int trangThai);
}
