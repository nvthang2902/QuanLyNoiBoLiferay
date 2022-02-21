package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.NhanVienDuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienDuAnRepository extends JpaRepository<NhanVienDuAn, Integer> {
    List<NhanVienDuAn> findNhanVienDuAnsByNhanVien_Id(Integer id);

    List<NhanVienDuAn> findNhanVienDuAnsByDuAn_Id(Integer id);
}
