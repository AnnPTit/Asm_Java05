package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.GioHangService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.SanPhamService;
import com.example.demo.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@org.springframework.stereotype.Controller
public class Controller {
    //    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/home")
    public String getHome(Model model,
                          @RequestParam(name = "curent_page", defaultValue = "0") int curent_page,
                          @RequestParam(defaultValue = "6") int pageSize,
                          HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = request.getSession().getAttribute("idUser");
        int numOfProductInCart = 0;
        if (user == null) {
            numOfProductInCart = gioHangService.countByTaiKhoan(3L);
        } else {
            numOfProductInCart = gioHangService.countByTaiKhoan(Long.valueOf(user.toString()));
        }
        session.setAttribute("numOfProductInCart", numOfProductInCart);
        session.setAttribute("curent_page", curent_page);
        session.setAttribute("pageSize", pageSize);
        getTableData(curent_page, pageSize, model, request);
        return "index";

    }

    public Page<SanPham> getTableData(int curent_page, int pageSize, Model model, HttpServletRequest request) {
        if (curent_page == 0 && pageSize == 0) {
            HttpSession session = request.getSession();
            curent_page = (int) session.getAttribute("curent_page");
            pageSize = (int) session.getAttribute("pageSize");
        }
        Pageable pageable = PageRequest.of(curent_page, pageSize);
        Page<SanPham> homePage = sanPhamService.findSanPhamByTrangThai(pageable);
        long totalItems = homePage.getTotalElements();
        int totalPages = homePage.getTotalPages();
        model.addAttribute("currentPage", curent_page);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("homePage", homePage);
        model.addAttribute("list", homePage.getContent());

        return homePage;
    }

    // Detail
    @GetMapping("/home/product/detail")
    public String getDetail(Model model, @RequestParam("id") Long id) {
        SanPham sanPham = sanPhamService.getDetailSanPham(id);
        model.addAttribute("sanpham", sanPham);
        List<ChiTietSanPham> chiTietSpList = chiTietSanPhamService.getListCtspByIdSp(id);
        model.addAttribute("chiTietSpList", chiTietSpList);
        model.addAttribute("listHinh", chiTietSpList.get(0).getListHinh());
        return "speaker-details";
    }


    // ------------------------------------------------Gio hang ------------------------------------------//
    // Them vao gio hang
    @GetMapping("/home/product/detail/addToCart")
    public String test(@RequestParam("id") Long id,
                       @RequestParam("quantity") String quantity,
                       Model model,
                       RedirectAttributes redirectAttributes,
                       HttpServletRequest request,
                       HttpSession session) {
        ChiTietSanPham chiTietSp = chiTietSanPhamService.getChiTietSanPhamById(id);
        if (quantity.isBlank()) {
            redirectAttributes.addFlashAttribute("message", "Please enter quantity !");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        }
        int soLuongMua = Integer.valueOf(quantity);
        if (soLuongMua < 0) {
            redirectAttributes.addFlashAttribute("message", "Please enter quantity > 0!");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        }
        // Get user
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Object maTaiKhoanObject = httpRequest.getSession().getAttribute("maTaiKhoan");

        if (maTaiKhoanObject == null) {
            TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByMa("TK003");
            GioHang gioHang = new GioHang();
            gioHang.setChiTietSp(chiTietSp);
            gioHang.setTaiKhoan(taiKhoan);
            gioHang.setSoLuong(soLuongMua);
            gioHang.setDonGia(chiTietSp.getGiaBan());
            gioHang.setTrangThai(1);
            if (gioHangService.add(gioHang)) {
                redirectAttributes.addFlashAttribute("message", "Add success!");
                return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
            }
            redirectAttributes.addFlashAttribute("message", "Add fail!");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        } else {
            String maTaiKhoan = (maTaiKhoanObject != null) ? maTaiKhoanObject.toString() : null;
            TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByMa(maTaiKhoan);
            //Kiem tra so luong mua
            if (soLuongMua > chiTietSp.getSoLuongTon()) {
                redirectAttributes.addFlashAttribute("message", "Number of products left " + chiTietSp.getSoLuongTon() + ", You cant not buy : " + soLuongMua);
                return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
            }
            // Kiem tra sp da ton tai chua => cap nhap so luong
            GioHang gioHang1 = gioHangService.getGHByCtspAndTk(chiTietSp.getId(), taiKhoan.getId());
            if (gioHang1 != null) {
                gioHang1.setSoLuong(gioHang1.getSoLuong() + soLuongMua);
                if (gioHangService.update(gioHang1)) {
                    redirectAttributes.addFlashAttribute("message", "Add success!");
                    int numOfProductInCart = gioHangService.countByTaiKhoan(taiKhoan.getId());
                    session.setAttribute("numOfProductInCart", numOfProductInCart);
                    return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
                }
            }
            GioHang gioHang = new GioHang();
            gioHang.setChiTietSp(chiTietSp);
            gioHang.setTaiKhoan(taiKhoan);
            gioHang.setSoLuong(soLuongMua);
            gioHang.setDonGia(chiTietSp.getGiaBan());
            gioHang.setTrangThai(1);
            if (gioHangService.add(gioHang)) {
                redirectAttributes.addFlashAttribute("message", "Add success!");
                int numOfProductInCart = gioHangService.countByTaiKhoan(taiKhoan.getId());
                session.setAttribute("numOfProductInCart", numOfProductInCart);
                return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
            }
            redirectAttributes.addFlashAttribute("message", "Add fail!");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        }
    }

    // View Giỏ hàng
    @GetMapping("/home/cart")
    public String viewCart(Model model,
                           HttpServletRequest request) {
        getGioHang(model, request);
        return "cart";
    }

    // Xoa gio hang
    @GetMapping("/home/cart/remove")
    public String removeCart(RedirectAttributes redirectAttributes,
                             @RequestParam("id") Long id) {
        gioHangService.remove(id);
        return "redirect:/home/cart";
    }

    @PostMapping("/home/cart/pay")
    public String processCartForm(@RequestParam(name = "selectedItems", defaultValue = "-1") String[] selectedItems,
                                  Model model,
                                  HttpServletRequest request) {

        boolean containsValue = false;
        for (String item : selectedItems) {
            if (item.equals("-1")) {
                containsValue = true;
                break;
            }
        }

        if (containsValue) {
            return "redirect:/home/cart";
        }

        List<GioHang> gioHangList = new ArrayList<>();
        List<String> filteredItems = new ArrayList<>();
        for (String item : selectedItems) {
            if (!item.contains(",")) {
                filteredItems.add(item);
            }
        }

        for (String item : filteredItems) {
            System.out.println("Selected item: " + item);
            GioHang gioHang = gioHangService.getById(Long.valueOf(item));
            gioHangList.add(gioHang);
        }
        float total = 0;
        for (GioHang gioHang : gioHangList
        ) {
            total = total + (gioHang.getSoLuong() * gioHang.getDonGia());
        }
        model.addAttribute("listGh", gioHangList);
        model.addAttribute("total", total);
        getGioHang(model, request);
        return "cart";
    }

    private void getGioHang(Model model, HttpServletRequest request) {
        HttpServletRequest httpRequest = request;
        List<GioHang> gioHangList = new ArrayList<>();
        Object idUser = httpRequest.getSession().getAttribute("idUser");
        Object username = httpRequest.getSession().getAttribute("user");
        if (username == null) {
            gioHangList = gioHangService.getGhOfUser(3L);
            model.addAttribute("gioHangList", gioHangList);
            model.addAttribute("user", null);
            return;
        }
        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByUsername(username.toString());
        Long userId = null;
        if (idUser instanceof Long) {
            userId = (Long) idUser;
        } else if (idUser instanceof String) {
            try {
                userId = Long.parseLong((String) idUser);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        gioHangList = gioHangService.getGhOfUser(userId);
        model.addAttribute("gioHangList", gioHangList);
        model.addAttribute("user", taiKhoan);

    }

    @PostMapping("/home/cart/buy")
    public String buyCart(@RequestParam("paymentMethod") String paymentMethod,
                          @RequestParam("tenNguoiNhan") String receiverName,
                          @RequestParam("sdt") String phoneNumber,
                          @RequestParam("diaChi") String address,
                          @RequestParam("id") List<Long> selectedItems,
                          Model model,
                          HttpServletRequest request) {
        // Xử lý logic mua hàng với danh sách các ID
        if (receiverName.isBlank()) {
            model.addAttribute("message", "Please enter Receiver's Name !");
            getGioHang(model, request);
            return "cart";
        }
        if (phoneNumber.isBlank()) {
            model.addAttribute("message", "Please enter Phone number !");
            getGioHang(model, request);
            return "cart";
        }
        //Mẫu số điện thoại: 10 chữ số bắt đầu bằng số 0
        String phonePattern = "0\\d{9}";

        boolean isValidPhoneNumber = Pattern.matches(phonePattern, phoneNumber);
        if (!isValidPhoneNumber) {
            model.addAttribute("message", "Please enter Phone number correct!");
            getGioHang(model, request);
            return "cart";
        }
        if (address.isBlank()) {

        }
        // tạo hóa đơn
        List<GioHang> hangList = new ArrayList<>();
        for (Long item : selectedItems) {
            GioHang gioHang = gioHangService.getById(item);
            hangList.add(gioHang);
        }
        // Tạo hóa đơn mua hàng
        UUID uuid = UUID.randomUUID();
        // Gen Ma
        // Convert UUID to a string representation
        String randomString = "HD" + uuid.toString();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTaiKhoan(hangList.get(0).getTaiKhoan());
        hoaDon.setMaHD(randomString);
        hoaDon.setNgayTao(new Date());
        hoaDon.setLoaiThanhToan(paymentMethod);
        hoaDon.setTenNguoiNhan(receiverName);
        hoaDon.setSdt(phoneNumber);
        hoaDon.setDiaChi(address);
        hoaDon.setTrangThai(1);
        hoaDonService.addHoaDon(hoaDon);
        // Tạo hóa đơn chi tiết
        for (GioHang gh : hangList
        ) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietSp(gh.getChiTietSp());
            hoaDonChiTiet.setSoLuong(gh.getSoLuong());
            hoaDonChiTiet.setDonGia(gh.getDonGia());
            hoaDonChiTiet.setTrangThai(1);
            hoaDonChiTietService.addHDCT(hoaDonChiTiet);
            gioHangService.updateTrangThaiGHById(gh.getId(), 2);
        }
        model.addAttribute("message", "Đặt hàng thành công ! Đơn hàng của bạn đang được xử lí !");
        getGioHang(model, request);
        return "cart";
    }

    // -------------------------------------end Gio hang --------------------------------------------------------------------//
    // Buy Now
    @GetMapping("/home/product/detail/buyNow")
    public String buyNow(@RequestParam("id") Long id,
                         @RequestParam("quantity") String quantity,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         HttpSession session,
                         HttpServletRequest request) {
        return "login";
    }

    @PostMapping("/home/product/detail/buyNow")
    public String buyNow(@RequestParam("id") Long id,
                         @RequestParam("quantity") String quantity,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes,
                         HttpSession session
    ) {
        // Có 2 trường hợp
        // Đã đăng nhập , chưa đăng nhập
        // Chưa đăng nhập  -> Fill vào giỏ hàng của guest -> Tiến hành thanh toán
        ChiTietSanPham chiTietSp = chiTietSanPhamService.getChiTietSanPhamById(id);
        if (quantity.isBlank()) {
            redirectAttributes.addFlashAttribute("message", "Please enter quantity !");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        }
        int soLuongMua = Integer.valueOf(quantity);
        if (soLuongMua < 0) {
            redirectAttributes.addFlashAttribute("message", "Please enter quantity > 0!");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        }
        // Check Login
        Object idUser = request.getSession().getAttribute("idUser");
        Object maTaiKhoanObject = request.getSession().getAttribute("maTaiKhoan");
        if (idUser == null) {
            TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByMa("TK003");
            GioHang gioHang = new GioHang();
            gioHang.setChiTietSp(chiTietSp);
            gioHang.setTaiKhoan(taiKhoan);
            gioHang.setSoLuong(soLuongMua);
            gioHang.setDonGia(chiTietSp.getGiaBan());
            gioHang.setTrangThai(1);
            if (gioHangService.add(gioHang)) {
                redirectAttributes.addFlashAttribute("message", "Add success!");
                redirectAttributes.addFlashAttribute("checked", gioHang.getChiTietSp());
                return "redirect:/home/cart";
            }
            redirectAttributes.addFlashAttribute("message", "Add fail!");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        } else {
            // Đã đăng nhập
            String maTaiKhoan = (maTaiKhoanObject != null) ? maTaiKhoanObject.toString() : null;
            TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByMa(maTaiKhoan);
            //Kiem tra so luong mua
            if (soLuongMua > chiTietSp.getSoLuongTon()) {
                redirectAttributes.addFlashAttribute("message", "Number of products left " + chiTietSp.getSoLuongTon() + ", You cant not buy : " + soLuongMua);
                return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
            }
            // Kiem tra sp da ton tai chua => cap nhap so luong
            GioHang gioHang1 = gioHangService.getGHByCtspAndTk(chiTietSp.getId(), taiKhoan.getId());
            if (gioHang1 != null) {
                gioHang1.setSoLuong(gioHang1.getSoLuong() + soLuongMua);
                if (gioHangService.update(gioHang1)) {
                    redirectAttributes.addFlashAttribute("message", "Add success!");
                    int numOfProductInCart = gioHangService.countByTaiKhoan(taiKhoan.getId());
                    session.setAttribute("numOfProductInCart", numOfProductInCart);
                    redirectAttributes.addFlashAttribute("checked", gioHang1.getChiTietSp());
                    return "redirect:/home/cart";
                }
            }
            GioHang gioHang = new GioHang();
            gioHang.setChiTietSp(chiTietSp);
            gioHang.setTaiKhoan(taiKhoan);
            gioHang.setSoLuong(soLuongMua);
            gioHang.setDonGia(chiTietSp.getGiaBan());
            gioHang.setTrangThai(1);
            if (gioHangService.add(gioHang)) {
                redirectAttributes.addFlashAttribute("message", "Add success!");
                redirectAttributes.addFlashAttribute("checked", gioHang.getChiTietSp());
                int numOfProductInCart = gioHangService.countByTaiKhoan(taiKhoan.getId());
                session.setAttribute("numOfProductInCart", numOfProductInCart);

                return "redirect:/home/cart";
            }
            redirectAttributes.addFlashAttribute("message", "Add fail!");
            return "redirect:/home/product/detail?id=" + chiTietSp.getSanPham().getId();
        }
    }
}



