package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.VaiTroDTO;
import com.example.quanlynoiboapi.service.VaiTroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaitro")
public class VaiTroController {

    private final VaiTroService vaiTroService;

    @GetMapping
    public List<VaiTroDTO> getVaitros() {

        return vaiTroService.getVaiTros();
    }

    @GetMapping("/{id}")
    public VaiTroDTO getById(@PathVariable Integer id) {

        return vaiTroService.getVaiTroId(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody VaiTroDTO vaiTroDTO) {

        vaiTroService.addVaiTro(vaiTroDTO);
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody VaiTroDTO vaiTroDTO) {

        vaiTroService.addVaiTro(vaiTroDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        vaiTroService.delete(id);
    }
}
