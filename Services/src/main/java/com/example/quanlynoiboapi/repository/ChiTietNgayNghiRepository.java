package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.ChiTietNgayNghi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietNgayNghiRepository extends JpaRepository<ChiTietNgayNghi,Integer> {
    List<ChiTietNgayNghi> findChiTietNgayNghiByNhanVien_UserId(long id);
}