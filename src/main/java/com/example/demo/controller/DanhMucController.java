package com.example.demo.controller;


import com.example.demo.entity.DanhMuc;
import com.example.demo.service.DanhMucService;
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
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;

    @GetMapping("/admin/table/danh-muc")
    public String getTableLayout(Model model,
                                 @RequestParam(name = "curent_page", defaultValue = "0") int curent_page,
                                 @RequestParam(defaultValue = "5") int pageSize,
                                 HttpServletRequest request) {
        ChiTietSanPhamController.getSecsionPage(model, curent_page, pageSize, request);
        model.addAttribute("danhMuc", new DanhMuc());
        getTableData(curent_page, pageSize, model, request);
        return "admin";
    }

    public Page<DanhMuc> getTableData(int curent_page, int pageSize, Model model, HttpServletRequest request) {
        if (curent_page == 0 && pageSize == 0) {
            HttpSession session = request.getSession();
            curent_page = (int) session.getAttribute("curent_page");
            pageSize = (int) session.getAttribute("pageSize");
        }
        Pageable pageable = PageRequest.of(curent_page, pageSize);
        Page<DanhMuc> danhMucPage = danhMucService.getPaginatedProducts(pageable);
        int size = danhMucService.getAll().size();
        int numPage = 1;
        numPage = size / pageSize;
        if (size % pageSize != 0) {
            numPage++;
        }
        model.addAttribute("numPage", numPage);
        model.addAttribute("danhMucPage", danhMucPage);
        return danhMucPage;
    }

    @GetMapping("/admin/category/add")
    public String viewAdd(Model model, HttpServletRequest request
    ) {
        model.addAttribute("danhMuc", new DanhMuc());
        getTableData(0, 0, model, request);
        return "admin";
    }

    @PostMapping("/admin/category/add")
    public String add(Model model,
                      @Valid @ModelAttribute("danhMuc") DanhMuc danhMuc,
                      BindingResult result,
                      HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            model.addAttribute("page", "danh-muc");
            getTableData(0, 0, model, request);
            return "admin";
        }
        danhMuc.setTrangThai(1);
        if (danhMucService.add(danhMuc) == false) {
            model.addAttribute("ma_err", "Mã đã tồn tại");
            model.addAttribute("page", "danh-muc");
            getTableData(0, 0, model, request);
            return "admin";
        }
        danhMucService.add(danhMuc);
        model.addAttribute("page", "danh-muc");
        getTableData(0, 0, model, request);
        model.addAttribute("message", "Thêm thành công  ! ");
        return "admin";
    }

    @GetMapping("/admin/category/detail")
    public String detail(Model model,
                         @RequestParam("id") Long id,
                         HttpServletRequest request) {
        DanhMuc danhMuc = danhMucService.findById(id);
        model.addAttribute("danhMuc", danhMuc);
        model.addAttribute("page", "danh-muc");
        getTableData(0, 0, model, request);
        return "admin";
    }

    @GetMapping("/admin/category/remove")
    public String remove(Model model,
                         @RequestParam("id") Long id,
                         HttpServletRequest request) {
        DanhMuc danhMuc = danhMucService.findById(id);
        danhMuc.setTrangThai(0);
        danhMucService.update(danhMuc);
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("page", "danh-muc");
        model.addAttribute("message", "Xóa thành công ! ");
        getTableData(0, 0, model, request);
        return "admin";
    }

    @GetMapping("/admin/category/update")
    public String viewUpdate(Model model,
                             @RequestParam("id") Long id) {
        DanhMuc danhMuc = danhMucService.findById(id);
        model.addAttribute("item", danhMuc);
        model.addAttribute("itemName", "category");
        model.addAttribute("tilte", "Update Danh Muc");
        model.addAttribute("page", "view-update-san-pham");
        return "admin";
    }

    @PostMapping("/admin/category/update")
    public String update(Model model,
                         @Valid @ModelAttribute("item") DanhMuc danhMuc,
                         BindingResult result,
                         HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("page", "view-update-san-pham");
            return "admin";
        }
        DanhMuc existingDanhMuc = danhMucService.findById(danhMuc.getId()); // Điều chỉnh phương thức tương ứng
        existingDanhMuc.setTen(danhMuc.getTen());
        danhMucService.update(existingDanhMuc);
        model.addAttribute("page", "danh-muc");
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("message", "Cập nhật thành công ! ");
        getTableData(0, 0, model, request);
        return "admin";
    }
}
