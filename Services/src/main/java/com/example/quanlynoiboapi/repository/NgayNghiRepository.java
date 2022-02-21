package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.NgayNghi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgayNghiRepository extends JpaRepository<NgayNghi,Integer> {
}