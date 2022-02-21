package com.example.quanlynoiboapi.controller;


import com.example.quanlynoiboapi.dto.NhanVienDTO;
import com.example.quanlynoiboapi.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nhanVien")
public class NhanVienController {

    private final NhanVienService nhanVienService;

    @GetMapping
    public List<NhanVienDTO> getAllNhanVien(){
        return nhanVienService.getAllNhanVien();
    }

    @PostMapping("/add")
    public void createNhanVien(@RequestBody @Valid NhanVienDTO nhanVienDTO) {
        nhanVienService.addNhanVien(nhanVienDTO);
    }

    @GetMapping("/{id}")
    public NhanVienDTO getNhanVienById(@PathVariable  Integer id){
        return nhanVienService.getNhanVienId(id);
    }

    @GetMapping("/user/{id}")
    public NhanVienDTO getNhanVienByUserId(@PathVariable  Integer id){
        return nhanVienService.getNhanVienByUserId(id);
    }

    @PutMapping("/update/{id}")
    public void updateNhanVien(@RequestBody @Valid NhanVienDTO nhanVienDTO) {
        nhanVienService.updateNhanVien(nhanVienDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNhanVien(@PathVariable Integer id){
        nhanVienService.deleteNhanVien(id);
    }
}