package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.ChiTietCongTacDTO;
import com.example.quanlynoiboapi.mapper.ChiTietCongTacMapper;
import com.example.quanlynoiboapi.model.ChiTietCongTac;
import com.example.quanlynoiboapi.repository.ChiTietCongTacRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChiTietCongTacSerVice {

    private final ChiTietCongTacRepository chiTietCongTacReponsitory;
    private final ChiTietCongTacMapper chiTietCongTacMapper;

    public List<ChiTietCongTacDTO> getAll() {

        return chiTietCongTacMapper.toChiTietCongTacs(chiTietCongTacReponsitory.findAll());
    }

    public ChiTietCongTacDTO getById(Integer id) {

        Optional<ChiTietCongTac> chiTietCongTac = chiTietCongTacReponsitory.findById(id);

        if (chiTietCongTac.isPresent()) {

            return chiTietCongTacMapper.toChiTietCongTacDTO(chiTietCongTac.get());
        }

        return null;
    }

    public void add(ChiTietCongTacDTO chiTietCongTacDTO) {

        chiTietCongTacReponsitory.save(chiTietCongTacMapper.toChiTietCongTac(chiTietCongTacDTO));
    }

    public void update(ChiTietCongTacDTO chiTietCongTacDTO) {

        chiTietCongTacReponsitory.save(chiTietCongTacMapper.toChiTietCongTac(chiTietCongTacDTO));
    }

    public void delete(Integer id) {

        chiTietCongTacReponsitory.deleteById(id);
    }

    public List<ChiTietCongTacDTO> getByCongTac(Integer id) {

        return chiTietCongTacMapper.toChiTietCongTacs(chiTietCongTacReponsitory.findChiTietCongTacsByCongTac_Id(id));
    }

}