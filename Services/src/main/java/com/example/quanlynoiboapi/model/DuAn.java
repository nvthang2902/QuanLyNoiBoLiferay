package com.example.quanlynoiboapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@Entity
    public class DuAn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "The gender is required.")
    private String tenDuAn;
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngayBatDau;
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngayKetThuc;
    @NotNull
    private TrangThaiDuAn trangThai;

    @OneToMany(mappedBy = "duAn",fetch = FetchType.LAZY)
    @JsonBackReference
    List<NhanVienDuAn> nhanVienDuAns;

    @OneToMany(mappedBy = "duAn",fetch = FetchType.LAZY)
    @JsonBackReference
    List<CongTac> congTacs;

}
