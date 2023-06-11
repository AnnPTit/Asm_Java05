package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.DanhMuc;
import com.example.demo.entity.Hinh;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NhaSanXuat;
import com.example.demo.entity.SanPham;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.DanhMucService;
import com.example.demo.service.HinhService;
import com.example.demo.service.MauSacService;
import com.example.demo.service.NhaSanXuatService;
import com.example.demo.service.SanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChiTietSanPhamController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private DanhMucService danhMucService;
    @Autowired
    private NhaSanXuatService nhaSanXuatService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private HinhService hinhService;

    @GetMapping("/admin/table/chi-tiet-san-pham")
    private String getAllChiTietSp(Model model,
                                   @RequestParam(name = "curent_page", defaultValue = "0") int curent_page,
                                   HttpServletRequest request) {
        int pageSize = 5;
        getSecsionPage(model, curent_page, pageSize, request);
        getTableData(curent_page, pageSize, model, request);
        return "admin";
    }

    static void getSecsionPage(Model model, @RequestParam(name = "curent_page", defaultValue = "0") int curent_page, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String page = uri.substring(uri.lastIndexOf("/") + 1);
        session.setAttribute("curent_page", curent_page);
        session.setAttribute("pageSize", pageSize);
        model.addAttribute("page", page);
    }

    public Page<ChiTietSanPham> getTableData(int curent_page, int pageSize, Model model, HttpServletRequest request) {
        if (curent_page == 0 && pageSize == 0) {
            HttpSession session = request.getSession();
            curent_page = (int) session.getAttribute("curent_page");
            pageSize = (int) session.getAttribute("pageSize");
        }
        Pageable pageable = PageRequest.of(curent_page, pageSize);
        Page<ChiTietSanPham> chiTietSpPage = chiTietSanPhamService.getPaginatedProducts(pageable);
        int size = chiTietSanPhamService.getAll().size();
        int numPage = 1;
        numPage = size / pageSize;
        if (size % pageSize != 0) {
            numPage++;
        }
        model.addAttribute("numPage", numPage);
        model.addAttribute("chiTietSpPage", chiTietSpPage);
        return chiTietSpPage;
    }

    @GetMapping("/admin/productDetail/add")
    private String viewAdd(Model model) {
        model.addAttribute("chiTietSp", new ChiTietSanPham());
        model.addAttribute("page", "view-add-chi-tiet-sp");
        return getString(model);
    }


    @PostMapping("/admin/productDetail/add")
    private String addChiTietSP(Model model,
                                @Valid @ModelAttribute("chiTietSp") ChiTietSanPham chiTietSp,
                                BindingResult result,
                                @RequestParam("hinh") MultipartFile[] hinhFiles) {

        if (result.hasErrors()) {
            model.addAttribute("message", "Không Thành công");
            model.addAttribute("page", "view-add-chi-tiet-sp");
            return getString(model);
        }
        if (chiTietSp.getGiaNhap() > chiTietSp.getGiaBan()) {
            model.addAttribute("message", "Giá nhập < giá bán !");
            model.addAttribute("page", "view-add-chi-tiet-sp");
            return getString(model);
        }
        // Hinh anh
        List<Hinh> listHinh = new ArrayList<>();
        savePicture(chiTietSp, hinhFiles, listHinh);
        chiTietSp.setListHinh(listHinh);
        chiTietSp.setTrangThai(1);
        if (chiTietSanPhamService.add(chiTietSp)) {
            model.addAttribute("message", "Thành công");
        }
        model.addAttribute("page", "view-add-chi-tiet-sp");
        getString(model);
        model.addAttribute("chiTietSp", new ChiTietSanPham());
        return "admin";
    }

    @NotNull
    private String getString(Model model) {
        List<SanPham> listSanPham = sanPhamService.getAll();
        List<DanhMuc> listDanhMuc = danhMucService.getAll();
        List<MauSac> listMauSac = mauSacService.getAll();
        List<NhaSanXuat> listNSX = nhaSanXuatService.getAll();
        model.addAttribute("listSanPham", listSanPham);
        model.addAttribute("listDanhMuc", listDanhMuc);
        model.addAttribute("listMauSac", listMauSac);
        model.addAttribute("listNSX", listNSX);
        return "admin";
    }

    @GetMapping("/admin/productDetail/update")
    private String viewUpdate(Model model,
                              @RequestParam("id") Long id) {
        ChiTietSanPham chiTietSp = chiTietSanPhamService.getChiTietSanPhamById(id);
        model.addAttribute("chiTietSp", chiTietSp);
        model.addAttribute("listHinh", chiTietSp.getListHinh());
        getString(model);
        model.addAttribute("page", "view-update-chi-tiet-sp");
        return "admin";
    }

    @PostMapping("/admin/productDetail/update")
    private String update(Model model,
                          @Valid @ModelAttribute("chiTietSp") ChiTietSanPham chiTietSp,
                          BindingResult result,
                          @RequestParam("hinh") MultipartFile[] hinhFiles
    ) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Không thành công !");
            model.addAttribute("page", "view-update-chi-tiet-sp");
            ChiTietSanPham chiTietSp1 = chiTietSanPhamService.getChiTietSanPhamById(chiTietSp.getId());
            model.addAttribute("listHinh", chiTietSp1.getListHinh());
            return getString(model);

        }
        if (chiTietSp.getGiaNhap() > chiTietSp.getGiaBan()) {
            model.addAttribute("message", "Giá nhập < Giá bán  !");
            model.addAttribute("page", "view-update-chi-tiet-sp");
            ChiTietSanPham chiTietSp1 = chiTietSanPhamService.getChiTietSanPhamById(chiTietSp.getId());
            model.addAttribute("listHinh", chiTietSp1.getListHinh());
            return getString(model);
        }
        List<Hinh> listHinh = new ArrayList<>();
        ChiTietSanPham chiTietSp1 = chiTietSanPhamService.getChiTietSanPhamById(chiTietSp.getId());
        listHinh = chiTietSp1.getListHinh();
        // Nếu thêm hình -> Add vào list
        savePicture(chiTietSp, hinhFiles, listHinh);
        chiTietSp1.setListHinh(listHinh);
        chiTietSp1.setSanPham(chiTietSp.getSanPham());
        chiTietSp1.setDanhMuc(chiTietSp.getDanhMuc());
        chiTietSp1.setMauSac(chiTietSp.getMauSac());
        chiTietSp1.setNsx(chiTietSp.getNsx());
        chiTietSp1.setNamBh(chiTietSp.getNamBh());
        chiTietSp1.setSoLuongTon(chiTietSp.getSoLuongTon());
        chiTietSp1.setGiaNhap(chiTietSp.getGiaNhap());
        chiTietSp1.setGiaBan(chiTietSp.getGiaBan());
        chiTietSp1.setMota(chiTietSp.getMota());
        chiTietSp1.setTrangThai(1);
        if (chiTietSanPhamService.update(chiTietSp1)) {
            model.addAttribute("message", "Cập nhật thành công !");
            model.addAttribute("listHinh", listHinh);
            getString(model);
        }
        model.addAttribute("page", "view-update-chi-tiet-sp");
        return "admin";
    }

    private void savePicture(@ModelAttribute("chiTietSp") @Valid ChiTietSanPham chiTietSp,
                             @RequestParam("hinh") MultipartFile[] hinhFiles,
                             List<Hinh> listHinh) {
        if (hinhFiles != null && hinhFiles.length > 0) {
            for (MultipartFile hinhFile : hinhFiles) {
                if (!hinhFile.isEmpty()) {
                    try {
                        String fileName = hinhFile.getOriginalFilename();
                        String filePath = "D:/JAVA_5/Asm_java5/src/main/resources/static/assets/img/speakers/" + fileName;
                        String filePathForSql = "/assets/img/speakers/" + fileName;
                        File dest = new File(filePath);
                        // Luu hinh anh vao thu muc
                        hinhFile.transferTo(dest);
                        System.out.println(filePath);
                        // Tao doi tuong hinh
                        Hinh hinh = new Hinh();
                        hinh.setUrl(filePathForSql);
                        hinh.setChiTietSp(chiTietSp);
                        hinh.setTrangThai(1);
                        // add hinh vao list
                        listHinh.add(hinh);


                    } catch (IOException e) {
                        // Xử lý lỗi khi lưu file
                        // ...
                        e.printStackTrace();

                    }
                }
            }
        }

    }
    @GetMapping("/admin/productDetail/update/removePicture")
    public String removePicture(Model model,
                                @RequestParam("id") Long id) {
        Hinh hinh = hinhService.getHinhById(id);
        hinhService.remove(id);
        viewUpdate(model, hinh.getChiTietSp().getId());
        return "redirect:http://localhost:2003/admin/productDetail/update?id=" + hinh.getChiTietSp().getId();
    }

    @GetMapping("/admin/productDetail/remove")
    public String remove(Model model, @RequestParam("id") Long id,
                         HttpServletRequest request) {
        if (chiTietSanPhamService.remove(id)) {
            model.addAttribute("message", "Xóa thành công !");
            int pageSize = 5;
            getSecsionPage(model, 0, pageSize, request);
            getTableData(0, pageSize, model, request);
            model.addAttribute("page", "chi-tiet-san-pham");
            return "admin";
        }
        model.addAttribute("message", "Xóa thất bại  !");
        int pageSize = 5;
        getSecsionPage(model, 0, pageSize, request);
        getTableData(0, pageSize, model, request);
        model.addAttribute("page", "chi-tiet-san-pham");
        return "admin";
    }

}
