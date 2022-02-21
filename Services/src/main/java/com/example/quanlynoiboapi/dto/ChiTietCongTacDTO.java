package com.example.quanlynoiboapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChiTietCongTacDTO implements Serializable {

    private Integer id;
    private String tenChiPhi;
    private String donViTinh;
    private Integer soLuong;
    private double donGia;
    private double thanhTien;
    private CongTacDTO congTac;
    private boolean trangThai;
    private String hoaDon;
}
