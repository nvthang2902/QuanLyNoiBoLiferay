package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.NgayNghiNhanVienDTO;
import com.example.quanlynoiboapi.service.NgayNghiNhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ngayNghiNhanVien")
public class NgayNghiNhanVienController {

    private final NgayNghiNhanVienService ngayNghiNhanVienService;

    @GetMapping
    public List<NgayNghiNhanVienDTO> AllNgayNghiNhanVien() {
        return ngayNghiNhanVienService.getAllNgayNghiNhanViens();
    }

    @PostMapping("/add")
    public void create(@RequestBody @Valid NgayNghiNhanVienDTO ngayNghiNhanVienDTO) {
        ngayNghiNhanVienService.add(ngayNghiNhanVienDTO);
    }

    @GetMapping("/{id}")
    public NgayNghiNhanVienDTO getById(@PathVariable Integer id) {
        return ngayNghiNhanVienService.getNgayNghiNhanVienById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody @Valid NgayNghiNhanVienDTO ngayNghiNhanVienDTO) {
        ngayNghiNhanVienService.update(ngayNghiNhanVienDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNN(@PathVariable Integer id) {
        ngayNghiNhanVienService.delete(id);
    }

    @GetMapping("/user/{id}")
    public List<NgayNghiNhanVienDTO> getNhanVienByNNNVId(@PathVariable  long id){
        return ngayNghiNhanVienService.getNhanVienByNNNVId(id);
    }
    @GetMapping("ngay/{nam}")
    public List<NgayNghiNhanVienDTO> getNam(@PathVariable Integer nam){
        return ngayNghiNhanVienService.getNam(nam);
    }

    @GetMapping("ngay/{nam}/{id}")
    public NgayNghiNhanVienDTO getNamAndNhanVienId(@PathVariable Integer nam,@PathVariable long id){
        return ngayNghiNhanVienService.getNamAndNhanVienId(nam,id);
    }
}