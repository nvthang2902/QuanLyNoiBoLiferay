package com.example.quanlynoiboapi.service;

import com.example.quanlynoiboapi.dto.HoaDonDTO;
import com.example.quanlynoiboapi.mapper.HoaDonMapper;
import com.example.quanlynoiboapi.model.HoaDon;
import com.example.quanlynoiboapi.repository.HoaDonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HoaDonService {

    private final HoaDonRepository hoaDonRepository;
    private final HoaDonMapper hoaDonMapper;

    public void addHoaDon(MultipartFile file, HoaDonDTO hoaDonDTO) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        hoaDonDTO = new HoaDonDTO(hoaDonDTO.getId(), fileName, file.getContentType(), file.getBytes());

        hoaDonRepository.save(hoaDonMapper.toHoaDon(hoaDonDTO));
    }

    public HoaDon getFile(Integer id) {

        return hoaDonRepository.findById(id).get();
    }

    public HoaDon findHoaDonByName(String name) {

        return hoaDonRepository.findHoaDonByName(name);
    }

}
