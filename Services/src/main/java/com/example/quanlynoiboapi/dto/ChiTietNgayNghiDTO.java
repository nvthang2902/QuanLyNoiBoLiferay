package com.example.quanlynoiboapi.dto;

import com.example.quanlynoiboapi.model.TinhTrangDuyet;
import com.example.quanlynoiboapi.model.TrangThaiBuoiXinNghi;
import com.example.quanlynoiboapi.model.TrangThaiNghi;
import lombok.Data;
import java.util.Date;

@Data
public class ChiTietNgayNghiDTO {

    private Integer id;
    private Date ngayXinNghi;
    private float soNgayNghi;
    private TrangThaiBuoiXinNghi buoiNghi;
    private TrangThaiNghi trangThai;
    private TinhTrangDuyet tinhTrangDuyet;
    private String lyDo;

    private NhanVienDTO nhanVien;
}