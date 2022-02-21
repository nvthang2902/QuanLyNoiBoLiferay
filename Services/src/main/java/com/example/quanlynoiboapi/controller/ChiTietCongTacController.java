package com.example.quanlynoiboapi.controller;

import com.example.quanlynoiboapi.dto.ChiTietCongTacDTO;
import com.example.quanlynoiboapi.dto.CongTacDTO;
import com.example.quanlynoiboapi.model.TrangThaiCongTac;
import com.example.quanlynoiboapi.service.ChiTietCongTacSerVice;
import com.example.quanlynoiboapi.service.CongTacService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chitietcongtac")
public class ChiTietCongTacController {

    private final ChiTietCongTacSerVice chiTietCongTacSerVice;
    private final CongTacService congTacService;

    @GetMapping
    public List<ChiTietCongTacDTO> getAll() {

        return chiTietCongTacSerVice.getAll();
    }

    @PostMapping("/add")
    public void create(@RequestBody ChiTietCongTacDTO chiTietCongTacDTO) {

        chiTietCongTacSerVice.add(chiTietCongTacDTO);
    }

    @GetMapping("/{id}")
    public ChiTietCongTacDTO getById(@PathVariable Integer id) {

        return chiTietCongTacSerVice.getById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody ChiTietCongTacDTO chiTietCongTacDTO) {

        chiTietCongTacSerVice.update(chiTietCongTacDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        chiTietCongTacSerVice.delete(id);
    }

    @GetMapping("/congtac/{id}")
    public List<ChiTietCongTacDTO> getByCongTac(@PathVariable Integer id) {

        return chiTietCongTacSerVice.getByCongTac(id);
    }

    @PostMapping("/dongy/{id}")
    public void dongY(@PathVariable Integer id) {

        CongTacDTO congTacDTO = congTacService.getCongTacById(id);
        congTacDTO.setTrangThai(TrangThaiCongTac.DADUYET);
        congTacService.addCongTac(congTacDTO);
    }

    @PostMapping("/tuchoi/{id}")
    public void tuChoi(@PathVariable Integer id) {

        CongTacDTO congTacDTO = congTacService.getCongTacById(id);
        congTacDTO.setTrangThai(TrangThaiCongTac.DATUCHOI);
        congTacService.addCongTac(congTacDTO);
    }

    @PostMapping("/dataochiphi/{id}")
    public void daTaoChiPhi(@PathVariable Integer id) {

        CongTacDTO congTacDTO = congTacService.getCongTacById(id);
        congTacDTO.setTrangThai(TrangThaiCongTac.DATAOCHIPHI);
        congTacService.addCongTac(congTacDTO);
    }

    @PostMapping("/chohoanung/{id}")
    public void choHoanUng(@PathVariable Integer id) {

        CongTacDTO congTacDTO = congTacService.getCongTacById(id);
        congTacDTO.setTrangThai(TrangThaiCongTac.CHOHOANUNG);
        congTacService.addCongTac(congTacDTO);
    }

    @PostMapping("/dahoanung/{id}")
    public void daHoanUng(@PathVariable Integer id) {

        CongTacDTO congTacDTO = congTacService.getCongTacById(id);
        congTacDTO.setTrangThai(TrangThaiCongTac.DAHOANUNG);
        congTacService.addCongTac(congTacDTO);
    }

    @PostMapping("/duyet/{id}")
    public void duyet(@PathVariable Integer id) {

        ChiTietCongTacDTO chiTietCongTacDTO = chiTietCongTacSerVice.getById(id);
        chiTietCongTacDTO.setTrangThai(true);
        chiTietCongTacSerVice.add(chiTietCongTacDTO);
    }

    @PostMapping("/khongduyet/{id}")
    public void khongDuyet(@PathVariable Integer id) {

        ChiTietCongTacDTO chiTietCongTacDTO = chiTietCongTacSerVice.getById(id);
        chiTietCongTacDTO.setTrangThai(false);
        chiTietCongTacSerVice.add(chiTietCongTacDTO);
    }
}
