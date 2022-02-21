package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.CongTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongTacRepository extends JpaRepository<CongTac, Integer> {

    List<CongTac> findCongTacsByDuAn_Id(Integer id);

    List<CongTac> findCongTacsByNhanVien_Id(Integer id);
}
