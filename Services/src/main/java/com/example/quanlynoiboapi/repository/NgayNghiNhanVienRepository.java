package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.NgayNghiNhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface NgayNghiNhanVienRepository extends JpaRepository<NgayNghiNhanVien,Integer> {
    List<NgayNghiNhanVien> findNgayNghiNhanVienByNhanVien_UserId(long userId);
    List<NgayNghiNhanVien> findNgayNghiNhanVienByNam(Integer nam);
    NgayNghiNhanVien findNgayNghiNhanVienByNamAndNhanVien_UserId(Integer nam,long userId);
}