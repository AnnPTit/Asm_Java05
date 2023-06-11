package com.example.demo.service;

import com.example.demo.entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService {
    boolean addHDCT(HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet findById(Long id);

    List<HoaDonChiTiet> findByIdHoaDonAndTrangThai(Long idHd, int trangThai);

    int updateTTHDCTByIdHD(Long idHd, int trangThai);

    List<HoaDonChiTiet> findHDCTByAccountIdAndTrangThai(Long accountId, int trangThai, int trangThaiHDCT);

    boolean removeHdct(Long id);

    int updateTTHDCTById(Long id, int trangThai);

    List<HoaDonChiTiet> getProductCancelOfUser(Long idtk);

    int countHoaDonChiTietByHdctId(Long hdctId);
}
