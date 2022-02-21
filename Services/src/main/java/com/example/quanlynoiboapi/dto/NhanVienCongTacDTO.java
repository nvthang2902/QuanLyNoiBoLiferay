package com.example.quanlynoiboapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NhanVienCongTacDTO implements Serializable {

    private Integer id;
    private NhanVienDTO nhanVien;
    private CongTacDTO congTac;
}
