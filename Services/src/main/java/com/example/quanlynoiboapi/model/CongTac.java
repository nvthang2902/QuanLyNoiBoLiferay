package com.example.quanlynoiboapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@Entity
public class CongTac implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "duAnId")
    private DuAn duAn;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nhanVienId")
    private NhanVien nhanVien;

    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngayBatDau;
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngayKetThuc;
    @NotEmpty
    private String nhiemVu;
    @NotEmpty
    private String diaDiem;
    @NotEmpty
    private String ghiChu;
    @NotNull
    private TrangThaiCongTac trangThai;
    private double tienUng;

    @OneToMany(mappedBy = "congTac", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ChiTietCongTac> chiTietCongTacs;

    @OneToMany(mappedBy = "congTac", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<NhanVienCongTac> nhanVienCongTacs;

}
