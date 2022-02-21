package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.ChiTietNgayNghiDTO;
import com.example.quanlynoiboapi.mapper.ChiTietNgayNghiMapper;
import com.example.quanlynoiboapi.model.ChiTietNgayNghi;
import com.example.quanlynoiboapi.repository.ChiTietNgayNghiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChiTietNgayNghiService {

    private final ChiTietNgayNghiRepository chiTietNgayNghiReponsitory;
    private final ChiTietNgayNghiMapper chiTietNgayNghiMapper;

    public List<ChiTietNgayNghiDTO> getChiTietNgayNghis() {
        return chiTietNgayNghiMapper.toChiTietNgayNghiDTOs(chiTietNgayNghiReponsitory.findAll());
    }

    public void addChiTietNgayNghi(ChiTietNgayNghiDTO chiTietNgayNghiDTO) {
        chiTietNgayNghiReponsitory.save(chiTietNgayNghiMapper.toChiTietNgayNghi(chiTietNgayNghiDTO));
    }
    public void update(ChiTietNgayNghiDTO chiTietNgayNghiDTO) {
        chiTietNgayNghiReponsitory.save(chiTietNgayNghiMapper.toChiTietNgayNghi(chiTietNgayNghiDTO));
    }

    public void delete(Integer id) {
        chiTietNgayNghiReponsitory.deleteById(id);
    }

    public ChiTietNgayNghiDTO getChiTietNgayNghiById(Integer id) {
        Optional<ChiTietNgayNghi> chiTietNgayNghi = chiTietNgayNghiReponsitory.findById(id);
        if (chiTietNgayNghi.isPresent()) {
            return chiTietNgayNghiMapper.toChiTietNgayNghiDTO(chiTietNgayNghi.get());
        }

        return null;
    }

    public List<ChiTietNgayNghiDTO> getNhanVienByCTNNId(long id) {
        return chiTietNgayNghiMapper.toChiTietNgayNghiDTOs(chiTietNgayNghiReponsitory.findChiTietNgayNghiByNhanVien_UserId(id));
    }


}
