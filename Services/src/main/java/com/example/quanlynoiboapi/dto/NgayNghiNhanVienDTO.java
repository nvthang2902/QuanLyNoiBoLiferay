package com.example.quanlynoiboapi.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class NgayNghiNhanVienDTO implements Serializable {

    private Integer id;
    private Double soNgayDaNghi;
    private Double soNgayNghiPhep;
    private Double soNgayNghiTon;
    private Integer nam;

    private NhanVienDTO nhanVien;
}