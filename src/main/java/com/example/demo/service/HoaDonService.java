package com.example.demo.service;

import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HoaDonService {

    boolean addHoaDon(HoaDon hoaDon);

    HoaDon getHoaDonByMa(String ma);

    HoaDon findById(Long id);

    HoaDon findByIdAndTrangThai(Long id, int trangThai);

    Page<HoaDon> findByTrangThai(Integer trangThai, Pageable pageable);

    Page<HoaDon> findAll(Pageable pageable);

    int xacNhanDonHangByMa(Long id);

    int updateTrangThaiHoaDonById(Long id, int trangThai);

    List<HoaDon> findHDByAccountIdAndTrangThai(Long accountId, int trangThai);
}
