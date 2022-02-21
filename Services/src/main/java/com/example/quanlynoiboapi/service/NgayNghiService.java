package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.NgayNghiDTO;
import com.example.quanlynoiboapi.mapper.NgayNghiMapper;
import com.example.quanlynoiboapi.model.NgayNghi;
import com.example.quanlynoiboapi.repository.NgayNghiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NgayNghiService {

    private final NgayNghiRepository ngayNghiReponsitory;
    private final NgayNghiMapper ngayNghiMapper;

    public List<NgayNghiDTO> getNgayNghis() {
        return ngayNghiMapper.toNgayNghiDTOs(ngayNghiReponsitory.findAll());
    }

    public void addNgayNghi(NgayNghiDTO ngayNghiDTO) {

        ngayNghiReponsitory.save(ngayNghiMapper.toNgayNghi(ngayNghiDTO));
    }

    public void deleteNgayNghi(Integer id) {

        ngayNghiReponsitory.deleteById(id);
    }

    public NgayNghiDTO getNgayNghiById(Integer id) {
        Optional<NgayNghi> ngayNghi = ngayNghiReponsitory.findById(id);
        if (ngayNghi.isPresent()) {
            return ngayNghiMapper.toNgayNghiDTO(ngayNghi.get());
        }

        return null;
    }
    public void update(NgayNghiDTO ngayNghiDTO){
        ngayNghiReponsitory.save(ngayNghiMapper.toNgayNghi(ngayNghiDTO));
    }

}
