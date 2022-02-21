package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.ChiTietCongTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietCongTacRepository extends JpaRepository<ChiTietCongTac, Integer> {

    List<ChiTietCongTac> findChiTietCongTacsByCongTac_Id(Integer id);
}
