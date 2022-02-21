package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.DuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuAnRepository extends JpaRepository<DuAn, Integer> {

    List<DuAn> findDuAnByNhanVienDuAns_NhanVien_UserId(long userId);

    List<DuAn> findDuAnById(Integer id);
}
