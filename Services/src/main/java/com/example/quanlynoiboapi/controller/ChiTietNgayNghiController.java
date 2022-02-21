package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.ChiPhiPhatSinhDTO;
import com.example.quanlynoiboapi.dto.ChiTietNgayNghiDTO;
import com.example.quanlynoiboapi.dto.NgayNghiNhanVienDTO;
import com.example.quanlynoiboapi.dto.OTDTO;
import com.example.quanlynoiboapi.model.TinhTrangDuyet;
import com.example.quanlynoiboapi.service.ChiTietNgayNghiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chiTietNgayNghi")
public class ChiTietNgayNghiController {

    private final ChiTietNgayNghiService chiTietNgayNghiService;

    @GetMapping
    public List<ChiTietNgayNghiDTO> getAllChiTietNgayNghis() {
        return chiTietNgayNghiService.getChiTietNgayNghis();
    }

    @GetMapping("/{id}")
    public ChiTietNgayNghiDTO getById(@PathVariable Integer id) {
        return chiTietNgayNghiService.getChiTietNgayNghiById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid ChiTietNgayNghiDTO chiTietNgayNghiDTO) {
        chiTietNgayNghiService.addChiTietNgayNghi(chiTietNgayNghiDTO);
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody @Valid ChiTietNgayNghiDTO chiTietNgayNghiDTO) {
        chiTietNgayNghiService.update(chiTietNgayNghiDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        chiTietNgayNghiService.delete(id);
    }

    @GetMapping("/user/{id}")
    public List<ChiTietNgayNghiDTO> getNhanVienByCTNNId(@PathVariable  long id){
        return chiTietNgayNghiService.getNhanVienByCTNNId(id);
    }

    @PostMapping("/accept/{id}")
    public void accept(@PathVariable Integer id) {
        ChiTietNgayNghiDTO chiTietNgayNghiDTO = chiTietNgayNghiService.getChiTietNgayNghiById(id);
        chiTietNgayNghiDTO.setTinhTrangDuyet(TinhTrangDuyet.DADUYET);
        chiTietNgayNghiService.addChiTietNgayNghi(chiTietNgayNghiDTO);
    }
    @PostMapping("/deny/{id}")
    public void denyOT(@PathVariable Integer id) {
        ChiTietNgayNghiDTO chiTietNgayNghiDTO = chiTietNgayNghiService.getChiTietNgayNghiById(id);
        chiTietNgayNghiDTO.setTinhTrangDuyet(TinhTrangDuyet.TUCHOI);
        chiTietNgayNghiService.addChiTietNgayNghi(chiTietNgayNghiDTO);
    }
}
