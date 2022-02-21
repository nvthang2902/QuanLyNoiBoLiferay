package com.example.quanlynoiboapi.repository;


import com.example.quanlynoiboapi.model.OT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OTRepository extends JpaRepository<OT,Integer> {
    List<OT> findOTByNhanVien_UserId(Long userId);

    List<OT> findOTByDuAn_Id(Integer id);

}
