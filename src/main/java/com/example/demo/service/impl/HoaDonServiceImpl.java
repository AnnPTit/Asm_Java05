package com.example.demo.service.impl;

import com.example.demo.entity.HoaDon;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public boolean addHoaDon(HoaDon hoaDon) {
        try {
            hoaDonRepository.save(hoaDon);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public HoaDon getHoaDonByMa(String ma) {
        try {
            return hoaDonRepository.findByMaHD(ma);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public HoaDon findById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public HoaDon findByIdAndTrangThai(Long id, int trangThai) {
        return hoaDonRepository.findByIdAndTrangThai(id, trangThai);
    }

    @Override
    public Page<HoaDon> findByTrangThai(Integer trangThai, Pageable pageable) {
        try {
            return hoaDonRepository.findByTrangThai(trangThai, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<HoaDon> findAll(Pageable pageable) {
        return hoaDonRepository.findAll(pageable);
    }

    @Override
    public int xacNhanDonHangByMa(Long id) {
        try {
            return hoaDonRepository.xacNhanDonHangByMa(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateTrangThaiHoaDonById(Long id, int trangThai) {
        try {
            return hoaDonRepository.updateTrangThaiHoaDonById(id, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<HoaDon> findHDByAccountIdAndTrangThai(Long accountId, int trangThai) {
        try {
            return hoaDonRepository.findHDByAccountIdAndTrangThai(accountId, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
