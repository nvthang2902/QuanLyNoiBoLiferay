package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.service.DuAnService;
import com.example.quanlynoiboapi.dto.DuAnDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/duan")
public class DuAnController {

    private final DuAnService duAnService;

    @GetMapping
    public List<DuAnDTO> getDuAns() {

        return duAnService.getDuAns();
    }

    @GetMapping("/{id}")
    public DuAnDTO getById(@PathVariable Integer id) {

        return duAnService.getDuAnById(id);
    }

    @GetMapping("/nhanvien/{id}")
    public List<DuAnDTO> getByNhanVien(@PathVariable Integer id) {

        return duAnService.getByNhanVien(id);
    }

    @GetMapping("/nhanvienuser/{userId}")
    public List<DuAnDTO> getByNhanVienByUserId(@PathVariable long userId) {

        return duAnService.findDuAnByNhanVien(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody @Valid DuAnDTO duAnDTO) {

        duAnService.addDuAn(duAnDTO);
        return ResponseEntity.ok("User is valid");
    }

    @PutMapping("update/{id}")
    public void edit(@RequestBody @Valid DuAnDTO duAnDTO) {

        duAnService.addDuAn(duAnDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        duAnService.delete(id);
    }

}
