package com.example.quanlynoiboapi.mapper;

import com.example.quanlynoiboapi.dto.CongTacDTO;
import com.example.quanlynoiboapi.dto.DuAnDTO;
import com.example.quanlynoiboapi.model.CongTac;
import com.example.quanlynoiboapi.model.DuAn;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CongTacMapper {

    CongTac toCongTac(CongTacDTO congTacDTO);

    CongTacDTO toCongTacDTO(CongTac congTac);

    List<CongTacDTO> toCongTacDTOs(List<CongTac> congTacs);
}
