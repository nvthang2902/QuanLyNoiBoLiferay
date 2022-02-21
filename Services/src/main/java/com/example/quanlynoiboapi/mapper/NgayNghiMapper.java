package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.NgayNghiDTO;
import com.example.quanlynoiboapi.model.NgayNghi;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface NgayNghiMapper {

    NgayNghi toNgayNghi(NgayNghiDTO ngayNghiDTO);
    NgayNghiDTO toNgayNghiDTO(NgayNghi ngayNghi);
    List<NgayNghiDTO> toNgayNghiDTOs(List<NgayNghi>ngayNghis);
}
