package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.VaiTroDTO;
import com.example.quanlynoiboapi.mapper.VaiTroMapper;
import com.example.quanlynoiboapi.model.VaiTro;
import com.example.quanlynoiboapi.repository.VaiTroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VaiTroService {

    private final VaiTroRepository vaiTroReponsitory;
    private final VaiTroMapper vaiTroMapper;

    public List<VaiTroDTO> getVaiTros() {

        return vaiTroMapper.toVaiTroDTOs(vaiTroReponsitory.findAll());
    }

    public void addVaiTro(VaiTroDTO vaiTroDTO) {

        vaiTroReponsitory.save(vaiTroMapper.toVaiTro(vaiTroDTO));
    }

    public void delete(Integer id) {

        vaiTroReponsitory.deleteById(id);
    }

    public VaiTroDTO getVaiTroId(Integer id) {

        Optional<VaiTro> vaiTro = vaiTroReponsitory.findById(id);

        if (vaiTro.isPresent()) {

            return vaiTroMapper.toVaiTroDTO(vaiTro.get());
        }

        return null;
    }
}
