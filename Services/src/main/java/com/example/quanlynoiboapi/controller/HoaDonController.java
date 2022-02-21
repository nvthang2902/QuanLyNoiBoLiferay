package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.HoaDonDTO;

import com.example.quanlynoiboapi.model.HoaDon;
import com.example.quanlynoiboapi.service.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hoadon")
public class HoaDonController {

    private final HoaDonService hoaDonService;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file, HoaDonDTO hoaDonDTO) throws IOException {

        hoaDonService.addHoaDon(file, hoaDonDTO);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {

        HoaDon hoaDon = hoaDonService.getFile(id);

        return ResponseEntity.ok()
                .body(hoaDon.getData());
    }

    @GetMapping("/{name}")
    public HoaDon getHoaDonByName(@PathVariable String name) {

        return hoaDonService.findHoaDonByName(name);
    }
}
