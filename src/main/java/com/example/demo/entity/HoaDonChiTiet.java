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
@Table(name = "hoadonchitiet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class HoaDonChiTiet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_hoadon", nullable = false)
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "id_ctsp", nullable = false)
    private ChiTietSanPham chiTietSp;
    @Column(name = "so_luong")
    private Integer soLuong;
    @Column(name = "don_gia")
    private Float donGia;
    @Column(name = "trang_thai")
    private Integer trangThai;
}
