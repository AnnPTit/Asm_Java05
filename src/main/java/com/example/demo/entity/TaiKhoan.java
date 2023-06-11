package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taikhoan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TaiKhoan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ma")
    private String ma;


    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]+$", message = "Username must contain at least one uppercase letter and one lowercase letter")
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "ten")
    private String ten;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;
    @Column(name = "ngaysinh")
    private Date ngaySinh;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must have 10 digits")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    @Column(name = "sdt")
    private String sdt;

    @NotBlank
    @Column(name = "diachi")
    private String diaChi;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must have between 8 and 20 characters")
    @Pattern.List({
            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")
    })
    @Column(name = "matkhau")
    private String matKhau;
    @Column(name = "chucdanh")
    private Integer chucDanh;
    @Column(name = "trang_thai")
    private Integer trangThai;
    @OneToMany(mappedBy = "taiKhoan", fetch = FetchType.LAZY)
    List<GioHang> listGioHang;
    @OneToMany(mappedBy = "taiKhoan", fetch = FetchType.LAZY)
    List<HoaDon> listHoaDon;
}
