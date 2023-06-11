package com.example.demo.controller;


import com.example.demo.entity.NhaSanXuat;
import com.example.demo.service.NhaSanXuatService;
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
public class NSXController {
    @Autowired
    private NhaSanXuatService nhaSanXuatService;

    @GetMapping("/admin/table/nsx")
    public String getTableLayout(Model model,
                                 @RequestParam(name = "curent_page", defaultValue = "0") int curent_page,
                                 @RequestParam(defaultValue = "5") int pageSize,
                                 HttpServletRequest request) {
        ChiTietSanPhamController.getSecsionPage(model, curent_page, pageSize, request);
        model.addAttribute("nsx", new NhaSanXuat());
        getTableData(curent_page, pageSize, model, request);
        return "admin";
    }

    public Page<NhaSanXuat> getTableData(int curent_page, int pageSize, Model model, HttpServletRequest request) {
        if (curent_page == 0 && pageSize == 0) {
            HttpSession session = request.getSession();
            curent_page = (int) session.getAttribute("curent_page");
            pageSize = (int) session.getAttribute("pageSize");
        }
        Pageable pageable = PageRequest.of(curent_page, pageSize);
        Page<NhaSanXuat> nsxPage = nhaSanXuatService.getPaginatedProducts(pageable);
        int size = nhaSanXuatService.getAll().size();
        int numPage = 1;
        numPage = size / pageSize;
        if (size % pageSize != 0) {
            numPage++;
        }
        model.addAttribute("numPage", numPage);
        model.addAttribute("nsxPage", nsxPage);
        return nsxPage;
    }

    @GetMapping("/admin/producer/add")
    public String viewAdd(Model model, HttpServletRequest request
    ) {
        model.addAttribute("nsx", new NhaSanXuat());
        getTableData(0, 0, model, request);
        return "admin";
    }

    @PostMapping("/admin/producer/add")
    public String add(Model model,
                            @Valid @ModelAttribute("nsx") NhaSanXuat nsx,
                            BindingResult result,
                            HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            model.addAttribute("page", "nsx");
            getTableData(0, 0, model, request);
            return "admin";
        }
        nsx.setTrangThai(1);
        if (nhaSanXuatService.add(nsx) == false) {
            model.addAttribute("ma_err", "Mã đã tồn tại");
            model.addAttribute("page", "nsx");
            getTableData(0, 0, model, request);
            return "admin";
        }
        nhaSanXuatService.add(nsx);
        model.addAttribute("page", "nsx");
        getTableData(0, 0, model, request);
        model.addAttribute("message", "Thêm thành công  ! ");
        return "admin";
    }

    @GetMapping("/admin/producer/detail")
    public String detail(Model model,
                         @RequestParam("id") Long id,
                         HttpServletRequest request) {
        NhaSanXuat nsx = nhaSanXuatService.findById(id);
        model.addAttribute("nsx", nsx);
        model.addAttribute("page", "nsx");
        getTableData(0, 0, model, request);
        return "admin";
    }

    @GetMapping("/admin/producer/remove")
    public String remove(Model model,
                         @RequestParam("id") Long id,
                         HttpServletRequest request) {
        NhaSanXuat nsx = nhaSanXuatService.findById(id);
        nsx.setTrangThai(3);
        nhaSanXuatService.update(nsx);
        model.addAttribute("nsx", new NhaSanXuat());
        model.addAttribute("page", "nsx");
        getTableData(0, 0, model, request);
        model.addAttribute("message", "Xóa thành công ! ");
        return "admin";
    }

    @GetMapping("/admin/producer/update")
    public String viewUpdate(Model model,
                             @RequestParam("id") Long id) {
        NhaSanXuat nsx = nhaSanXuatService.findById(id);
        model.addAttribute("item", nsx);
        model.addAttribute("itemName", "producer");
        model.addAttribute("tilte", "Update NSX");
        model.addAttribute("page", "view-update-san-pham");
        return "admin";
    }

    @PostMapping("/admin/producer/update")
    public String update(Model model,
                               @Valid @ModelAttribute("item") NhaSanXuat nsx,
                               BindingResult result,
                               HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("page", "view-update-san-pham");
            return "admin";
        }
        NhaSanXuat existingNSX = nhaSanXuatService.findById(nsx.getId()); // Điều chỉnh phương thức tương ứng
        existingNSX.setTen(nsx.getTen());
        nhaSanXuatService.update(existingNSX);
        model.addAttribute("page", "nsx");
        model.addAttribute("nsx", new NhaSanXuat());
        model.addAttribute("message", "Cập nhật thành công ! ");
        getTableData(0, 0, model, request);
        return "admin";
    }
}
