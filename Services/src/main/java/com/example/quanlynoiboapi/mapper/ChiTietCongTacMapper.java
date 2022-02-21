package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.ChiTietCongTacDTO;
import com.example.quanlynoiboapi.model.ChiTietCongTac;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChiTietCongTacMapper {

    ChiTietCongTac toChiTietCongTac(ChiTietCongTacDTO chiTietCongTacDTO);

    ChiTietCongTacDTO toChiTietCongTacDTO(ChiTietCongTac chiTietCongTac);

    List<ChiTietCongTacDTO> toChiTietCongTacs(List<ChiTietCongTac> chiTietCongTacs);
}
