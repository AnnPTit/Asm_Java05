package com.example.demo.controller;

import com.example.demo.entity.SanPham;
import com.example.demo.service.SanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;

@Controller
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;
    public List<SanPham> list = new ArrayList<>();

    @GetMapping("/admin/table/san-pham")
    public String getTableLayout(Model model,
                                 @RequestParam(name = "curent_page", defaultValue = "0") int curent_page,
                                 @RequestParam(defaultValue = "5") int pageSize,
                                 HttpServletRequest request) {
        ChiTietSanPhamController.getSecsionPage(model, curent_page, pageSize, request);
        model.addAttribute("sp", new SanPham());
        getTableData(curent_page, pageSize, model, request);
        return "admin";
    }

    public Page<SanPham> getTableData(int curent_page, int pageSize, Model model, HttpServletRequest request) {
        if (curent_page == 0 && pageSize == 0) {
            HttpSession session = request.getSession();
            curent_page = (int) session.getAttribute("curent_page");
            pageSize = (int) session.getAttribute("pageSize");
        }
        Pageable pageable = PageRequest.of(curent_page, pageSize);
        Page<SanPham> sanphamPage = sanPhamService.getPaginatedProducts(pageable);
        int size = sanPhamService.getAll().size();
        int numPage = 1;
        numPage = size / pageSize;
        if (size % pageSize != 0) {
            numPage++;
        }
        model.addAttribute("numPage", numPage);
        model.addAttribute("sanphamPage", sanphamPage);
        return sanphamPage;
    }

    @GetMapping("/admin/product/add")
    public String viewAdd(Model model, HttpServletRequest request
    ) {
        model.addAttribute("sp", new SanPham());
        model.addAttribute("page", "san-pham");
        getTableData(0, 0, model, request);
        return "admin";
    }

    @PostMapping("/admin/product/add")
    public String addSanPham(Model model,
                             @Valid @ModelAttribute("sp") SanPham sanPham,
                             BindingResult result,
                             HttpServletRequest request
    ) {
        System.out.println(sanPham.getMa());
        if (result.hasErrors()) {
            model.addAttribute("page", "san-pham");
            getTableData(0, 0, model, request);
            return "admin";
        }
        sanPham.setTrangThai(1);
        if (sanPhamService.add(sanPham) == false) {
            model.addAttribute("ma_err", "Mã sản phẩm đã tồn tại");
            model.addAttribute("page", "san-pham");
            getTableData(0, 0, model, request);
            return "admin";
        }
        model.addAttribute("page", "san-pham");
        model.addAttribute("message", "Thêm thành công  ! ");
        getTableData(0, 0, model, request);
        return "admin";

    }

    @GetMapping("/admin/product/detail")
    public String detailSanPham(@RequestParam("id") Long id,
                                Model model,
                                HttpServletRequest request) {
        SanPham sanPham = sanPhamService.findById(id);
        model.addAttribute("sp", sanPham);
        model.addAttribute("page", "san-pham");
        getTableData(0, 0, model, request);
        return "admin";
    }

    @GetMapping("/admin/product/remove")
    public String removeSanpham(@RequestParam("id") Long id,
                                Model model,
                                HttpServletRequest request) {
        SanPham sanPham = sanPhamService.findById(id);
        sanPham.setTrangThai(0);
        sanPhamService.update(sanPham);
        model.addAttribute("sp", new SanPham());
        model.addAttribute("page", "san-pham");
        model.addAttribute("message", "Xóa thành công  ! ");
        getTableData(0, 0, model, request);
        return "admin";
    }

    @GetMapping("/admin/product/update")
    public String viewUpdate(Model model,
                             @RequestParam("id") Long id) {
        SanPham sanPham = sanPhamService.findById(id);
        model.addAttribute("item", sanPham);
        model.addAttribute("itemName", "product");
        model.addAttribute("page", "view-update-san-pham");
        model.addAttribute("tilte", "Update San Pham");
        return "admin";
    }

    @PostMapping("/admin/product/update")
    public String updateSanPham(Model model,
                                @Valid @ModelAttribute("item") SanPham sanPham,
                                BindingResult result,
                                HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("page", "view-update-san-pham");
            return "admin";
        }
        SanPham existingSanPham = sanPhamService.findById(sanPham.getId()); // Điều chỉnh phương thức tương ứng
        existingSanPham.setTen(sanPham.getTen());
        sanPhamService.update(existingSanPham);
        model.addAttribute("page", "san-pham");
        model.addAttribute("sp", new SanPham());
        model.addAttribute("message", "Cập nhật thành công  ! ");
        getTableData(0, 0, model, request);
        return "admin";
    }

}
