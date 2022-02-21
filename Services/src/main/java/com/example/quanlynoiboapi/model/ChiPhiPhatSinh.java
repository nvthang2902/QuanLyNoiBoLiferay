package com.example.quanlynoiboapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ChiPhiPhatSinh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String tenChiPhi;
    @NotNull
    @DateTimeFormat(pattern = "MM-yyyy")
    private Date thang;
    @NotNull
    private String noiDung;
    @NotNull
    private double soTien;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nhanVienId")
    private NhanVien nhanVien;

}