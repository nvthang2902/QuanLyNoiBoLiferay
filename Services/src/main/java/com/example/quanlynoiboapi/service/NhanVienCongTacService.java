package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.NhanVienCongTacDTO;
import com.example.quanlynoiboapi.mapper.NhanVienCongTacMapper;
import com.example.quanlynoiboapi.model.NhanVienCongTac;
import com.example.quanlynoiboapi.repository.NhanVienCongTacRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NhanVienCongTacService {

    private final NhanVienCongTacRepository nhanVienCongTacReponsitory;
    private final NhanVienCongTacMapper nhanVienCongTacMapper;

    public List<NhanVienCongTacDTO> getAll() {

        return nhanVienCongTacMapper.toNhanVienCongTacDTOs(nhanVienCongTacReponsitory.findAll());
    }

    public void add(NhanVienCongTacDTO nhanVienCongTacDTO) {

        nhanVienCongTacReponsitory.save(nhanVienCongTacMapper.toNhanVienCongTac(nhanVienCongTacDTO));
    }

    public void delete(Integer id) {

        nhanVienCongTacReponsitory.deleteById(id);
    }

    public NhanVienCongTacDTO getById(Integer id) {

        Optional<NhanVienCongTac> nhanVienCongTac = nhanVienCongTacReponsitory.findById(id);

        if (nhanVienCongTac.isPresent()) {

            return nhanVienCongTacMapper.toNhanVienCongTacDTO(nhanVienCongTac.get());
        }

        return null;
    }

    public List<NhanVienCongTacDTO> getByCongTac(Integer id) {

        return nhanVienCongTacMapper.toNhanVienCongTacDTOs(nhanVienCongTacReponsitory.findNhanVienCongTacsByCongTacId(id));
    }

}
