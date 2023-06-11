package com.example.demo.service.impl;

import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.repository.HoaDonChiTietRepository;
import com.example.demo.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public boolean addHDCT(HoaDonChiTiet hoaDonChiTiet) {
        try {
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public HoaDonChiTiet findById(Long id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoaDonChiTiet> findByIdHoaDonAndTrangThai(Long idHd, int trangThai) {
        try {
            return hoaDonChiTietRepository.findByIdHoaDonAndTrangThai(idHd, trangThai);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int updateTTHDCTByIdHD(Long idHd, int trangThai) {
        try {
            return hoaDonChiTietRepository.updateTTHDCTByIdHD(idHd, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<HoaDonChiTiet> findHDCTByAccountIdAndTrangThai(Long accountId, int trangThai, int trangThaiHDCT) {
        try {
            return hoaDonChiTietRepository.findHDCTByAccountIdAndTrangThai(accountId, trangThai, trangThaiHDCT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removeHdct(Long id) {
        try {
            hoaDonChiTietRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int updateTTHDCTById(Long id, int trangThai) {
        try {
            return hoaDonChiTietRepository.updateTTHDCTById(id, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<HoaDonChiTiet> getProductCancelOfUser(Long idtk) {
        try {
            return hoaDonChiTietRepository.getProductCancelOfUser(idtk);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int countHoaDonChiTietByHdctId(Long hdctId) {
        try {
            return hoaDonChiTietRepository.countHoaDonChiTietByHdctId(hdctId);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
