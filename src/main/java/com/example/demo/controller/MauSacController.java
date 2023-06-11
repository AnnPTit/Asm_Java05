package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.service.MauSacService;
import com.example.demo.service.impl.MauSacServiceImpl;
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

@Controller
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

    @GetMapping("/admin/table/mau-sac")
    public String getTableLayout(Model model,
                                 @RequestParam(name = "curent_page", defaultValue = "0") int curent_page,
                                 @RequestParam(defaultValue = "5") int pageSize,
                                 HttpServletRequest request) {
        ChiTietSanPhamController.getSecsionPage(model, curent_page, pageSize, request);
        model.addAttribute("mau", new MauSac());
        getTableData(curent_page, pageSize, model, request);
        return "admin";
    }

    public Page<MauSac> getTableData(int curent_page, int pageSize, Model model, HttpServletRequest request) {
        if (curent_page == 0 && pageSize == 0) {
            HttpSession session = request.getSession();
            curent_page = (int) session.getAttribute("curent_page");
            pageSize = (int) session.getAttribute("pageSize");
        }
        Pageable pageable = PageRequest.of(curent_page, pageSize);
        Page<MauSac> mauSacPage = mauSacService.getPaginatedProducts(pageable);
        int size = mauSacService.getAll().size();
        int numPage = 1;
        numPage = size / pageSize;
        if (size % pageSize != 0) {
            numPage++;
        }
        model.addAttribute("numPage", numPage);
        model.addAttribute("mauSacPage", mauSacPage);
        return mauSacPage;
    }

    @GetMapping("/admin/color/add")
    public String viewAdd(Model model, HttpServletRequest request
    ) {
        model.addAttribute("mau", new MauSac());
        getTableData(0, 0, model, request);
        return "admin";
    }

    @PostMapping("/admin/color/add")
    public String addMauSac(Model model,
                            @Valid @ModelAttribute("mau") MauSac mauSac,
                            BindingResult result,
                            HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            model.addAttribute("page", "mau-sac");
            getTableData(0, 0, model, request);
            return "admin";
        }
        mauSac.setTrangThai(1);
        if (mauSacService.add(mauSac) == false) {
            model.addAttribute("ma_err", "Mã đã tồn tại");
            model.addAttribute("page", "mau-sac");
            getTableData(0, 0, model, request);
            return "admin";
        }
        mauSacService.add(mauSac);
        model.addAttribute("page", "mau-sac");
        getTableData(0, 0, model, request);
        model.addAttribute("message", "Thêm thành công  ! ");
        return "admin";
    }

    @GetMapping("/admin/color/detail")
    public String detail(Model model,
                         @RequestParam("id") Long id,
                         HttpServletRequest request) {
        MauSac mauSac = mauSacService.findById(id);
        model.addAttribute("mau", mauSac);
        model.addAttribute("page", "mau-sac");
        getTableData(0, 0, model, request);
        return "admin";
    }

    @GetMapping("/admin/color/remove")
    public String remove(Model model,
                         @RequestParam("id") Long id,
                         HttpServletRequest request) {
        MauSac mauSac = mauSacService.findById(id);
        mauSac.setTrangThai(3);
        mauSacService.update(mauSac);
        model.addAttribute("mau", new MauSac());
        model.addAttribute("page", "mau-sac");
        getTableData(0, 0, model, request);
        model.addAttribute("message", "Xóa thành công ! ");
        return "admin";
    }

    @GetMapping("/admin/color/update")
    public String viewUpdate(Model model,
                             @RequestParam("id") Long id) {
        MauSac mauSac = mauSacService.findById(id);
        model.addAttribute("item", mauSac);
        model.addAttribute("itemName", "color");
        model.addAttribute("tilte", "Update Mau Sac");
        model.addAttribute("page", "view-update-san-pham");
        return "admin";
    }

    @PostMapping("/admin/color/update")
    public String updateMauSac(Model model,
                               @Valid @ModelAttribute("item") MauSac mauSac,
                               BindingResult result,
                               HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("page", "view-update-san-pham");
            return "admin";
        }
        MauSac existingMauSac = mauSacService.findById(mauSac.getId()); // Điều chỉnh phương thức tương ứng
        existingMauSac.setTen(mauSac.getTen());
        mauSacService.update(existingMauSac);
        model.addAttribute("page", "mau-sac");
        model.addAttribute("mau", new MauSac());
        model.addAttribute("message", "Cập nhật thành công ! ");
        getTableData(0, 0, model, request);
        return "admin";
    }
}
