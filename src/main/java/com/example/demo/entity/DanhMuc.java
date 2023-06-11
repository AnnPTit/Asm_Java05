package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "danhmuc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DanhMuc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 6, message = "Ma >= 6 ky tu")
    @NotEmpty(message = "Ma khong duoc de trong !")
    private String ma;
    @NotEmpty(message = "Ten khong duoc de trong  !")
    private String ten;
    @Column(name = "trang_thai")
    private Integer trangThai;
    @OneToMany(mappedBy = "danhMuc", fetch = FetchType.LAZY)
    List<ChiTietSanPham> listCtsp;
}
