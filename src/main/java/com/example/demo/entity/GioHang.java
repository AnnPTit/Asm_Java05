package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "giohang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class GioHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_ctsp", nullable = false)
    private ChiTietSanPham chiTietSp;
    @ManyToOne
    @JoinColumn(name = "id_taikhoan", nullable = false)
    private TaiKhoan taiKhoan;
    @Column(name = "so_luong")
    private Integer soLuong;
    @Column(name = "don_gia")
    private float donGia;
    @Column(name = "trang_thai")
    private Integer trangThai;


}
