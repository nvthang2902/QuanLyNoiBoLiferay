package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {
    NhanVien findByUserId(Long id);



}
