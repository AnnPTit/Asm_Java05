package com.example.demo.controller;


import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.SanPham;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("sp", new SanPham());
        model.addAttribute("page", "admin-home");
        return "admin";
    }

    @GetMapping("/admin/table/don-hang")
    public String viewDonHang(Model model,
                              @RequestParam(name = "pageNumber", defaultValue = "0") int curent_page,
                              HttpSession session,
                              @RequestParam(name = "trangThaiDonHang", defaultValue = "1") int trangThaiDonHang) {
        int pageSize = 5;
        session.setAttribute("currentPageOfDonHang", curent_page);
        session.setAttribute("trangThaiDonHang", trangThaiDonHang);
        return getPaginated(model, curent_page, pageSize, trangThaiDonHang);
    }

    @NotNull
    private String getPaginated(Model model, int curent_page, int pageSize, int trangThai) {
        Pageable pageable = PageRequest.of(curent_page, pageSize);
        Page<HoaDon> page = null;
        if (trangThai == -1) {
            page = hoaDonService.findAll(pageable);
        } else {
            page = hoaDonService.findByTrangThai(trangThai, pageable);
        }
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        if (totalPages == 0) {
            totalPages = 1;
        }
        List<HoaDon> hoaDonList = page.getContent();
        model.addAttribute("hoaDonList", hoaDonList);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", curent_page);
        model.addAttribute("page", "don-hang");
        return "admin";
    }

    @GetMapping("/admin/table/don-hang/chi-tiet")
    public String viewDetail(Model model,
                             @RequestParam("id") Long id,
                             HttpSession session

    ) {
        HoaDon hoaDon = hoaDonService.findById(id);
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.findByIdHoaDonAndTrangThai(hoaDon.getId(), hoaDon.getTrangThai());
        model.addAttribute("hoaDonChiTietList", hoaDonChiTietList);
        int tongTien = 0;
        for (HoaDonChiTiet hdct : hoaDonChiTietList) {
            tongTien = tongTien + (hdct.getSoLuong() * Math.round(hdct.getDonGia().floatValue()));
        }
        model.addAttribute("tongTien", tongTien);
        int pageSize = 5;
        Object curent_page = session.getAttribute("currentPageOfDonHang");
        int cuPage = 0;
        if (curent_page != null) {
            cuPage = Integer.valueOf(curent_page.toString());
        }
        Object trangThai = session.getAttribute("trangThaiDonHang");
        int tt = -1;
        if (trangThai != null) {
            tt = Integer.valueOf(trangThai.toString());
        }
        return getPaginated(model, cuPage, pageSize, tt);
    }

    @GetMapping("/admin/don-hang/xac-nhan-don-hang")
    public String OrderConfirmation(Model model,
                                    @RequestParam("id") Long id,
                                    RedirectAttributes redirectAttributes) {
        HoaDon hoaDon = hoaDonService.findByIdAndTrangThai(id, 1);
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDon.getHoaDonChiTietList();
        // Check số lượng trước khi xác nhận
        for (HoaDonChiTiet hdct : hoaDonChiTietList
        ) {
            if (hdct.getSoLuong() > hdct.getChiTietSp().getSoLuongTon()) {
                System.out.println("ok");
                redirectAttributes.addFlashAttribute("message", "Xác nhận KHÔNG thành công đơn hàng " + hoaDon.getMaHD() + " Vì sản phẩm đã hết hàng ! ");
                return "redirect:/admin/table/don-hang";
            }
        }
        // Khi xác nhận => Update trạng thái hóa đơn và hóa đơn chi tiết về => 2
        // Cập nhật lại số lượng sản phẩm
        if (hoaDonService.xacNhanDonHangByMa(id) != 0) {
            hoaDonChiTietService.updateTTHDCTByIdHD(id, 2);
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietList
            ) {
                ChiTietSanPham chiTietSp = hoaDonChiTiet.getChiTietSp();
                chiTietSp.setSoLuongTon(chiTietSp.getSoLuongTon() - hoaDonChiTiet.getSoLuong());
                chiTietSanPhamService.update(chiTietSp);
            }
        }
        redirectAttributes.addFlashAttribute("message", "Xác nhận thành công đơn hàng !");
        return "redirect:/admin/table/don-hang";
    }

    @GetMapping("/admin/don-hang/van-chuyen-don-hang")
    public String shipDonHang(Model model,
                              @RequestParam("id") Long id,
                              RedirectAttributes redirectAttributes) {
        HoaDon hoaDon = hoaDonService.findByIdAndTrangThai(id, 2);
        hoaDon.setNgayShip(new Date());
        hoaDon.setTrangThai(3);
        if (hoaDonChiTietService.updateTTHDCTByIdHD(id, 3) != 0) {
            redirectAttributes.addFlashAttribute("message", "Thành công ! Đơn hàng được vận chuyển :  " + hoaDon.getMaHD());
            return "redirect:/admin/table/don-hang";
        }
        redirectAttributes.addFlashAttribute("message", "Không thành công ! Đơn hàng  :  " + hoaDon.getMaHD());
        return "redirect:/admin/table/don-hang";
    }

    @GetMapping("/admin/don-hang/hoan-thanh-don-hang")
    public String hoanThanhDonHang(@RequestParam("id") Long id,
                                   RedirectAttributes redirectAttributes) {
        HoaDon hoaDon = hoaDonService.findByIdAndTrangThai(id, 3);

        hoaDon.setNgayThanhToan(new Date());
        hoaDon.setTrangThai(4);
        if (hoaDonChiTietService.updateTTHDCTByIdHD(id, 4) != 0) {
            redirectAttributes.addFlashAttribute("message", "Thành công ! Đơn hàng  : " + hoaDon.getMaHD() + " đã hoàn thành ");
            return "redirect:/admin/table/don-hang";
        }
        redirectAttributes.addFlashAttribute("message", "Không thành công ! Đơn hàng  :  " + hoaDon.getMaHD());
        return "redirect:/admin/table/don-hang";
    }

    @GetMapping("/admin/don-hang/huy-don-hang")
    public String huyDonHang(@RequestParam("id") Long id,
                             RedirectAttributes redirectAttributes) {
        HoaDon hoaDon = hoaDonService.findByIdAndTrangThai(id, 1);
        hoaDon.setTrangThai(0);
        if (hoaDonChiTietService.updateTTHDCTByIdHD(id, 0) != 0) {
            redirectAttributes.addFlashAttribute("message", "Hủy thành công ! Đơn hàng  : " + hoaDon.getMaHD() + " đã hoàn thành ");
            return "redirect:/admin/table/don-hang";
        }
        redirectAttributes.addFlashAttribute("message", "Hủy không thành công ! Đơn hàng  :  " + hoaDon.getMaHD());
        return "redirect:/admin/table/don-hang";
    }
}

