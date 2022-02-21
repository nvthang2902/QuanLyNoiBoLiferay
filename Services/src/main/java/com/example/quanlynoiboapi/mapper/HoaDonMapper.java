package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.HoaDonDTO;
import com.example.quanlynoiboapi.model.HoaDon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HoaDonMapper {

    HoaDon toHoaDon(HoaDonDTO hoaDonDTO);
}
