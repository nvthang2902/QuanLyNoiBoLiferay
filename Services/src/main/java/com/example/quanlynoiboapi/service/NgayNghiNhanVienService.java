package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.NgayNghiNhanVienDTO;
import com.example.quanlynoiboapi.mapper.NgayNghiNhanVienMapper;
import com.example.quanlynoiboapi.model.NgayNghiNhanVien;
import com.example.quanlynoiboapi.repository.NgayNghiNhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NgayNghiNhanVienService {

    private final NgayNghiNhanVienRepository ngayNghiNhanVienReponsitory;
    private final NgayNghiNhanVienMapper ngayNghiNhanVienMapper ;

    public List<NgayNghiNhanVienDTO> getAllNgayNghiNhanViens() {
        return ngayNghiNhanVienMapper.toNgayNghiNhanVienDTOS(ngayNghiNhanVienReponsitory.findAll());
    }

    public void add(NgayNghiNhanVienDTO ngayNghiNhanVienDTO) {

        ngayNghiNhanVienReponsitory.save(ngayNghiNhanVienMapper.toNgayNghiNhanVien(ngayNghiNhanVienDTO));
    }

    public void delete(Integer id) {

        ngayNghiNhanVienReponsitory.deleteById(id);
    }

    public NgayNghiNhanVienDTO getNgayNghiNhanVienById(Integer id) {
        Optional<NgayNghiNhanVien> ngayNghiNhanVien = ngayNghiNhanVienReponsitory.findById(id);
        if (ngayNghiNhanVien.isPresent()) {
            return ngayNghiNhanVienMapper.toNgayNghiNhanVienDTO(ngayNghiNhanVien.get());
        }

        return null;
    }
    public void update(NgayNghiNhanVienDTO ngayNghiNhanVienDTO){
        ngayNghiNhanVienReponsitory.save(ngayNghiNhanVienMapper.toNgayNghiNhanVien(ngayNghiNhanVienDTO));
    }

    public List<NgayNghiNhanVienDTO> getNhanVienByNNNVId(long id) {
        return ngayNghiNhanVienMapper.toNgayNghiNhanVienDTOS(ngayNghiNhanVienReponsitory.findNgayNghiNhanVienByNhanVien_UserId(id));
    }

    public List<NgayNghiNhanVienDTO> getNam(Integer nam) {
        return ngayNghiNhanVienMapper.toNgayNghiNhanVienDTOS(ngayNghiNhanVienReponsitory.findNgayNghiNhanVienByNam(nam));
    }

    public NgayNghiNhanVienDTO getNamAndNhanVienId(Integer nam,long id) {
        return ngayNghiNhanVienMapper.toNgayNghiNhanVienDTO(ngayNghiNhanVienReponsitory.findNgayNghiNhanVienByNamAndNhanVien_UserId(nam,id));
    }

}
