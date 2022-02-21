package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    HoaDon findHoaDonByName(String name);
}
