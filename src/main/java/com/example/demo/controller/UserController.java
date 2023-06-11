package com.example.demo.controller;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/home/user/myOder")
    public String viewMyOder(Model model,
                             @RequestParam(name = "trangThai", defaultValue = "1") int trangThai,
                             HttpServletRequest request
    ) {
        // Get user
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Object maTaiKhoanObject = httpRequest.getSession().getAttribute("maTaiKhoan");
        String maTaiKhoan = (maTaiKhoanObject != null) ? maTaiKhoanObject.toString() : null;
        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByMa(maTaiKhoan);
        List<HoaDonChiTiet> hoaDonChiTietList;
        if (trangThai == 0) {
            hoaDonChiTietList = hoaDonChiTietService.getProductCancelOfUser(taiKhoan.getId());
        } else {
            hoaDonChiTietList = hoaDonChiTietService.findHDCTByAccountIdAndTrangThai(taiKhoan.getId(), trangThai, trangThai);
        }

        float total = 0;
        for (HoaDonChiTiet chiTiet : hoaDonChiTietList
        ) {
            total = total + (chiTiet.getSoLuong() * chiTiet.getDonGia());
        }
        model.addAttribute("hoaDonChiTietList", hoaDonChiTietList);
        model.addAttribute("total", total);
        return "myOder";
    }

    @GetMapping("/home/myOder/huy-don-hang")
    public String removeProduct(RedirectAttributes redirectAttributes,
                                @RequestParam("id") Long id) {
        int sumOfHdct = hoaDonChiTietService.countHoaDonChiTietByHdctId(id);
        if (sumOfHdct == 1) {
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.findById(id);
            HoaDon hoaDon = hoaDonChiTiet.getHoaDon();
            hoaDonService.updateTrangThaiHoaDonById(hoaDon.getId(), 0);
        }
        if (hoaDonChiTietService.updateTTHDCTById(id, 0) != 0) {
            redirectAttributes.addFlashAttribute("message", "Remove !");
            return "redirect:/home/user/myOder?trangThai=1";
        }
        redirectAttributes.addFlashAttribute("message", "fail !");
        return "redirect:/home/user/myOder?trangThai=1";
    }

    @GetMapping("/home/myOder/nhan-don-hang")
    public String hoanThanhDonHang(@RequestParam("id") Long id,
                                   RedirectAttributes redirectAttributes) {
        HoaDon hoaDon = hoaDonService.findByIdAndTrangThai(id, 4);
        hoaDon.setNgayNhan(new Date());
        hoaDon.setTrangThai(5);
        if (hoaDonChiTietService.updateTTHDCTByIdHD(id, 5) != 0) {
            redirectAttributes.addFlashAttribute("message", "Thành công ! Đơn hàng  : " + hoaDon.getMaHD() + " đã hoàn thành ");
            return "redirect:/home/user/myOder?trangThai=5";
        }
        redirectAttributes.addFlashAttribute("message", "Không thành công ! Đơn hàng  :  " + hoaDon.getMaHD());
        return "redirect:/home/user/myOder?trangThai=5";
    }
}
