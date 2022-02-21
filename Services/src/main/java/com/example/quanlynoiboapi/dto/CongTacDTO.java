package com.example.quanlynoiboapi.dto;

import com.example.quanlynoiboapi.model.TrangThaiCongTac;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CongTacDTO implements Serializable {

    private Integer id;
    private DuAnDTO duAn;
    private NhanVienDTO nhanVien;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String nhiemVu;
    private String diaDiem;
    private String ghiChu;
    private TrangThaiCongTac trangThai;
    private double tienUng;
}
