package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.NhanVienCongTacDTO;
import com.example.quanlynoiboapi.model.NhanVienCongTac;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NhanVienCongTacMapper {

    NhanVienCongTac toNhanVienCongTac(NhanVienCongTacDTO nhanVienCongTacDTO);

    NhanVienCongTacDTO toNhanVienCongTacDTO(NhanVienCongTac nhanVienCongTac);

    List<NhanVienCongTacDTO> toNhanVienCongTacDTOs(List<NhanVienCongTac> nhanVienCongTacs);
}
