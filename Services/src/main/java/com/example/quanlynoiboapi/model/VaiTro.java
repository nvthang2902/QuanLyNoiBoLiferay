package com.example.quanlynoiboapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class VaiTro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String tenVaiTro;

    @OneToMany(mappedBy = "vaiTro", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<NhanVienDuAn> nhanVienDuAns;

}
