package system.hoadon.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.hoadon.clients.HoaDonClient;
import system.hoadon.dto.HoaDonDTO;

import java.io.File;

@Service
@RequiredArgsConstructor
public class HoaDonService {

    private final HoaDonClient hoaDonClient;

    public void uploadHoaDon(File file) {

        hoaDonClient.uploadFile(file);
    }

    public HoaDonDTO findByName(String name) {

        return hoaDonClient.getByName(name);
    }

    public String getImageUrl() {

        return hoaDonClient.getUrl();
    }
}
