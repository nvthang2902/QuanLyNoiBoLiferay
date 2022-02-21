package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.ChiTietNgayNghiDTO;
import com.example.quanlynoiboapi.dto.NgayNghiDTO;
import com.example.quanlynoiboapi.model.ChiTietNgayNghi;
import com.example.quanlynoiboapi.model.NgayNghi;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChiTietNgayNghiMapper {

    ChiTietNgayNghi toChiTietNgayNghi(ChiTietNgayNghiDTO chiTietNgayNghiDTO);
    ChiTietNgayNghiDTO toChiTietNgayNghiDTO(ChiTietNgayNghi chiTietNgayNghi);
    List<ChiTietNgayNghiDTO> toChiTietNgayNghiDTOs(List<ChiTietNgayNghi>chiTietNgayNghis);

}
