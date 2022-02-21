package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.OTDTO;
import com.example.quanlynoiboapi.model.TinhTrangDuyet;
import com.example.quanlynoiboapi.service.OTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ot")
public class OTController {


    private final OTService otServices;

    @GetMapping
    public List<OTDTO> getOTs() {
        return otServices.getListOTs();
    }

    @GetMapping("/{id}")
    public OTDTO getById(@PathVariable Integer id) {
        return otServices.getOTById(id);
    }

    @PostMapping("/add")
    public void add( @RequestBody OTDTO otdto) {
        otServices.addOT(otdto);
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody OTDTO otdto) {
        otServices.addOT(otdto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        otServices.delete(id);
    }

    @GetMapping("nhanvien/{userId}")
    public List<OTDTO> getOTByNhanVien(@PathVariable Long userId) {
        return otServices.findOTByNhanVien(userId);
    }

    @PostMapping("/accept/{id}")
    public void acceptOT(@PathVariable Integer id) {
        OTDTO otdto = otServices.getOTById(id);
        otdto.setTinhTrangDuyet(TinhTrangDuyet.DADUYET);
        otServices.addOT(otdto);
    }
    @PostMapping("/deny/{id}")
    public void denyOT(@PathVariable Integer id) {
        OTDTO otdto = otServices.getOTById(id);
        otdto.setTinhTrangDuyet(TinhTrangDuyet.TUCHOI);
        otServices.addOT(otdto);
    }

    @GetMapping("/duan/{id}")
    public List<OTDTO> getOTByDuAnId(@PathVariable Integer id) {
        return otServices.findOTDuAnId(id);
    }


}