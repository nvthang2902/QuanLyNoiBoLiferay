package com.example.quanlynoiboapi.repository;

import com.example.quanlynoiboapi.model.ChiPhiPhatSinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiPhiPhatSinhRepository extends JpaRepository<ChiPhiPhatSinh,Integer> {
    List<ChiPhiPhatSinh> findChiPhiPhatSinhByNhanVien_UserId(long userId);
}
