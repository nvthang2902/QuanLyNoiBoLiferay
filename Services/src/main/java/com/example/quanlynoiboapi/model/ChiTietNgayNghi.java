package com.example.quanlynoiboapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class ChiTietNgayNghi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Date ngayXinNghi;
    private float soNgayNghi;
    private TrangThaiBuoiXinNghi buoiNghi;
    private TrangThaiNghi trangThai;
    private TinhTrangDuyet tinhTrangDuyet;
    @NotNull
    private String lyDo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nhanVienId")
    private NhanVien nhanVien;
}