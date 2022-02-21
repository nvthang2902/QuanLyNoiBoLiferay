package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.CongTacDTO;
import com.example.quanlynoiboapi.service.CongTacService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/congtac")
public class CongTacController {

    private final CongTacService congTacService;

    @GetMapping
    public List<CongTacDTO> getCongTacs() {

        return congTacService.getCongTacs();
    }

    @GetMapping("/{id}")
    public CongTacDTO getById(@PathVariable Integer id) {

        return congTacService.getCongTacById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CongTacDTO congTacDTO) {

        congTacService.addCongTac(congTacDTO);
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody @Valid CongTacDTO congTacDTO) {

        congTacService.addCongTac(congTacDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        congTacService.delete(id);
    }

    @GetMapping("/duan/{id}")
    public List<CongTacDTO> getByDuAn(@PathVariable Integer id) {

        return congTacService.getByDuAn(id);
    }

    @GetMapping("/nhanvien/{id}")
    public List<CongTacDTO> getByNhanVien(@PathVariable Integer id) {

        return congTacService.getByNhanVien(id);
    }
}
