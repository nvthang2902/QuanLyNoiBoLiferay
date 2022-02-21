package com.example.quanlynoiboapi.service;


import com.example.quanlynoiboapi.repository.OTRepository;
import com.example.quanlynoiboapi.dto.OTDTO;
import com.example.quanlynoiboapi.mapper.OTMapper;
import com.example.quanlynoiboapi.model.OT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OTService {
    private final OTRepository otRepository;
    private final OTMapper otMapper;

    public List<OTDTO> getListOTs() {
        return otMapper.toOTDTOs(otRepository.findAll());
    }

    public void addOT(OTDTO otdto) {
        otRepository.save(otMapper.toOT(otdto));
    }

    public void delete(Integer id) {
        otRepository.deleteById(id);
    }

    public OTDTO getOTById(Integer id) {
        Optional<OT> ot = otRepository.findById(id);
        if (ot.isPresent()) {
            return otMapper.toOTDTO(ot.get());
        }

        return null;
    }

    public List<OTDTO> findOTByNhanVien(Long userId) {
        return otMapper.toOTDTOs(otRepository.findOTByNhanVien_UserId(userId));
    }


    public List<OTDTO> findOTDuAnId(Integer id) {
        return otMapper.toOTDTOs(otRepository.findOTByDuAn_Id(id));
    }
}
