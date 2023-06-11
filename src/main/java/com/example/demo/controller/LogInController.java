package com.example.demo.controller;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.EmailService;
import com.example.demo.service.GioHangService;
import com.example.demo.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;
import java.util.UUID;

@Controller
public class LogInController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private EmailService emailService;


    // Login
    @GetMapping("/login")
    public String viewLogIn() {
        return "login";
    }

    @PostMapping("/login")
    public String logIn(@RequestParam("emailOrUser") String emailOrUser,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session ,
                        HttpServletResponse response) {
//        System.out.println(emailOrUser + "-" + password);
        if (emailOrUser.isBlank()) {
            model.addAttribute("message1", "Email không được để trống ! ");
            return "login";
        }
        if (password.isBlank()) {
            model.addAttribute("message2", "Mật khẩu không được để trống ! ");
            return "login";
        }
        TaiKhoan taiKhoan = null;
        if (emailOrUser.contains("@")) {
            taiKhoan = taiKhoanService.checkLogin(emailOrUser, password);
        } else {
            taiKhoan = taiKhoanService.checkLogInWithUser(emailOrUser, password);
        }
        if (taiKhoan == null) {
            model.addAttribute("message", "Tên tài khoản hoặc mật khẩu không chính xác !");
            return "login";
        }

        int numOfProductInCart = gioHangService.countByTaiKhoan(taiKhoan.getId());
        session.setAttribute("numOfProductInCart", numOfProductInCart);
        session.setAttribute("idUser", taiKhoan.getId());
        session.setAttribute("email", taiKhoan.getEmail());
        session.setAttribute("user", taiKhoan.getUsername());
        session.setAttribute("name", taiKhoan.getTen());
        session.setAttribute("maTaiKhoan", taiKhoan.getMa());
        session.setAttribute("chucDanh", taiKhoan.getChucDanh());

        if (taiKhoan.getChucDanh() == 1) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            return "redirect:/home";
        } else {
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logOut(Model model,
                         HttpSession session) {
        // Xóa các thuộc tính trong HttpSession
        session.removeAttribute("numOfProductInCart");
        session.removeAttribute("email");
        session.removeAttribute("user");
        session.removeAttribute("name");
        session.removeAttribute("maTaiKhoan");
        session.removeAttribute("idUser");
        session.invalidate(); // Xóa HttpSession

        // Điều hướng về trang đăng nhập
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @Valid @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
                           BindingResult result
            , @RequestParam("rePass") String rePass,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        if (!taiKhoan.getMatKhau().equals(rePass)) {
            model.addAttribute("errPass", "Password is not match !");
            return "register";
        }
        TaiKhoan taiKhoan1 = taiKhoanService.getTaiKhoanByUsername(taiKhoan.getUsername());
        if (taiKhoan1 != null) {
            model.addAttribute("errUserName", "Username is used ! Please chose another ");
            return "register";
        }
        UUID uuid = UUID.randomUUID();
        // Gen Ma
        // Convert UUID to a string representation
        String randomString = "TK" + uuid.toString();
        taiKhoan.setMa(randomString);
        taiKhoan.setChucDanh(1);
        taiKhoan.setTrangThai(1);
        if (taiKhoanService.add(taiKhoan)) {
            redirectAttributes.addFlashAttribute("message", "Congratulations on your successful registration !");
            return "redirect:/login";
        }

        return "register";
    }

    @GetMapping("/forgot-password")
    public String viewForgot() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPass(Model model, @RequestParam("emailOrUser") String emailOrUser,
                             HttpSession session) {
        System.out.println(emailOrUser);
        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByUsernameAndEmail(emailOrUser);
        if (taiKhoan == null) {
            model.addAttribute("err", "Email hoặc username không tồn tại !");
            return "forgot-password";
        }

        session.setAttribute("idTaiKhoan", taiKhoan.getId());
        String randomCode = generateRandomCode(6);
        session.setAttribute("randomCode", randomCode);
        emailService.sendEmail(taiKhoan.getEmail(), "Account verification", randomCode);
        return "forgot-password-2";
    }


    @PostMapping("/forgot-password-2")
    public String accountVerification(Model model, @RequestParam("code") String code,
                                      HttpSession session) {
        String randomCode = (String) session.getAttribute("randomCode");
        if (code.equalsIgnoreCase(randomCode)) {
            return "reset-password";
        } else {
            model.addAttribute("err", "Code khong chinh xac !");
            return "forgot-password-2";
        }
    }

    @PostMapping("/reset-password")
    public String resetPass(Model model,

                            @RequestParam("newPass") String newPass,
                            @RequestParam("rePass") String rePass, HttpSession session) {
        if (newPass.isBlank()) {
            model.addAttribute("err", "Khong duoc de trong !");
            return "reset-password";
        }
        if (!newPass.equals(rePass)) {
            model.addAttribute("err1", "Khong trung khop !");
            return "reset-password";
        }
        Long id = (Long) session.getAttribute("idTaiKhoan");
        model.addAttribute("id", id);
        taiKhoanService.updatePassById(id, newPass);
        session.removeAttribute("idTaiKhoan");
        session.removeAttribute("randomCode");
        return "redirect:/login";


    }

    public static String generateRandomCode(int length) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }
        return code.toString();
    }
}
