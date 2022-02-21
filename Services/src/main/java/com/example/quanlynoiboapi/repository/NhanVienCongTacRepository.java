package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.NhanVienCongTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienCongTacRepository extends JpaRepository<NhanVienCongTac, Integer> {

    List<NhanVienCongTac> findNhanVienCongTacsByCongTacId(Integer id);
}
