package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.NhanVienCongTacDTO;
import com.example.quanlynoiboapi.dto.NhanVienDTO;
import com.example.quanlynoiboapi.service.NhanVienCongTacService;
import com.example.quanlynoiboapi.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nhanviencongtac")
public class NhanVienCongTacController {

    private final NhanVienCongTacService nhanVienCongTacService;
    private final NhanVienService nhanVienService;

    @GetMapping
    public List<NhanVienCongTacDTO> getAll() {

        return nhanVienCongTacService.getAll();
    }

    @GetMapping("/{id}")
    public NhanVienCongTacDTO getById(@PathVariable Integer id) {

        return nhanVienCongTacService.getById(id);
    }

    @GetMapping("/congtac/{id}")
    public List<NhanVienCongTacDTO> getByCongTac(@PathVariable Integer id) {

        return nhanVienCongTacService.getByCongTac(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid NhanVienCongTacDTO nhanVienCongTacDTO) {

        nhanVienCongTacService.add(nhanVienCongTacDTO);
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody @Valid NhanVienCongTacDTO nhanVienCongTacDTO) {

        nhanVienCongTacService.add(nhanVienCongTacDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        nhanVienCongTacService.delete(id);
    }

    @GetMapping("/user/{id}")
    public NhanVienDTO getNhanVienByUserId(@PathVariable Integer id) {

        return nhanVienService.getNhanVienByUserId(id);
    }
}
