package com.example.quanlynoiboapi.mapper;


import com.example.quanlynoiboapi.dto.NgayNghiNhanVienDTO;
import com.example.quanlynoiboapi.model.NgayNghiNhanVien;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NgayNghiNhanVienMapper {

    NgayNghiNhanVien toNgayNghiNhanVien(NgayNghiNhanVienDTO ngayNghiNhanVienDTO);
    NgayNghiNhanVienDTO toNgayNghiNhanVienDTO(NgayNghiNhanVien ngayNghiNhanVien);
    List<NgayNghiNhanVienDTO> toNgayNghiNhanVienDTOS(List<NgayNghiNhanVien>ngayNghiNhanViens);
}
