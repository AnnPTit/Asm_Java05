package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "chitietsp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChiTietSanPham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private SanPham sanPham;
    @ManyToOne
    @JoinColumn(name = "id_producer", nullable = false)
    private NhaSanXuat nsx;
    @ManyToOne
    @JoinColumn(name = "id_color", nullable = false)
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private DanhMuc danhMuc;

    @NotNull
    @Min(0)
    @Column(name = "nam_Bh")
    private Integer namBh;

    @Column(name = "mota")
    private String mota;

    @Min(1)
    @Column(name = "sl_ton")
    @NotNull
    private Integer soLuongTon;

    @Min(1000)
    @Column(name = "gia_nhap")
    @NotNull
    private Float giaNhap;

    @Min(1000)
    @Column(name = "gia_ban")
    @NotNull
    private Float giaBan;

    @Column(name = "trang_thai")
    private Integer trangThai;
    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    List<GioHang> listGioHang;
    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    List<HoaDonChiTiet> listHDCT;
    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    List<Hinh> listHinh;
}
