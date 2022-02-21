package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.CongTacDTO;
import com.example.quanlynoiboapi.mapper.CongTacMapper;
import com.example.quanlynoiboapi.model.CongTac;
import com.example.quanlynoiboapi.repository.CongTacRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CongTacService {

    private final CongTacRepository congTacReponsitory;
    private final CongTacMapper congTacMapper;

    public List<CongTacDTO> getCongTacs() {

        return congTacMapper.toCongTacDTOs(congTacReponsitory.findAll());
    }

    public void addCongTac(CongTacDTO congTacDTO) {

        congTacReponsitory.save(congTacMapper.toCongTac(congTacDTO));
    }

    public void delete(Integer id) {

        congTacReponsitory.deleteById(id);
    }

    public CongTacDTO getCongTacById(Integer id) {

        Optional<CongTac> congTac = congTacReponsitory.findById(id);

        if (congTac.isPresent()) {

            return congTacMapper.toCongTacDTO(congTac.get());
        }

        return null;
    }

    public List<CongTacDTO> getByDuAn(Integer id) {

        return congTacMapper.toCongTacDTOs(congTacReponsitory.findCongTacsByDuAn_Id(id));
    }

    public List<CongTacDTO> getByNhanVien(Integer id) {

        return congTacMapper.toCongTacDTOs(congTacReponsitory.findCongTacsByNhanVien_Id(id));
    }
}
