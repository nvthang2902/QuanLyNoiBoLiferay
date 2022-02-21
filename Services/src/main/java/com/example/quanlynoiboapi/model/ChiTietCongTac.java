package com.example.quanlynoiboapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ChiTietCongTac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String tenChiPhi;
    @NotEmpty
    private String donViTinh;
    @NotNull
    private Integer soLuong;
    @NotNull
    private double donGia;
    @NotNull
    private double thanhTien;
    private boolean trangThai;
    private String hoaDon;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "congTacId")
    private CongTac congTac;
}
