package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.NgayNghiDTO;
import com.example.quanlynoiboapi.service.NgayNghiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ngayNghi")
public class NgayNghiController {

    private final NgayNghiService ngayNghiService;

    @GetMapping
    public List<NgayNghiDTO> AllNgayNghi() {
        return ngayNghiService.getNgayNghis();
    }

    @PostMapping("/add")
    public void createCPPS(@RequestBody @Valid NgayNghiDTO ngayNghiDTO) {
        ngayNghiService.addNgayNghi(ngayNghiDTO);
    }

    @GetMapping("/{id}")
    public NgayNghiDTO getNNById(@PathVariable Integer id) {
        return ngayNghiService.getNgayNghiById(id);
    }

    @PutMapping("/update/{id}")
    public void updateNN(@RequestBody @Valid NgayNghiDTO ngayNghiDTO) {
        ngayNghiService.update(ngayNghiDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNN(@PathVariable Integer id) {
        ngayNghiService.deleteNgayNghi(id);
    }

}