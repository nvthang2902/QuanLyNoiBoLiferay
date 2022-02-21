package system.chitietcongtac.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.chitietcongtac.clients.ChiTietCongTacClient;
import system.chitietcongtac.dto.ChiTietCongTacDTO;
import system.congtac.clients.CongTacClient;
import system.congtac.dto.CongTacDTO;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiTietCongTacService {

    private final ChiTietCongTacClient chiTietCongTacClient;
    private final CongTacClient congTacClient;

    public List<ChiTietCongTacDTO> getAll() {

        return chiTietCongTacClient.getAll();
    }

    public List<ChiTietCongTacDTO> getByCongTac(Integer id) {

        return chiTietCongTacClient.getByCongTac(id);
    }

    public ChiTietCongTacDTO findById(Integer id) {

        return chiTietCongTacClient.getById(id);
    }

    public ChiTietCongTacDTO getById(Integer id) {

        if (id > 0) {

            return findById(id);
        }

        return new ChiTietCongTacDTO();
    }

    public boolean save(ChiTietCongTacDTO chiTietCongTacDTO) {

        return chiTietCongTacClient.add(chiTietCongTacDTO);
    }

    public boolean delete(Integer id) {

        return chiTietCongTacClient.delete(id);
    }

    public boolean tuChoi(Integer id) {

        return chiTietCongTacClient.tuChoi(id);
    }

    public boolean dongY(Integer id) {

        return chiTietCongTacClient.dongY(id);
    }

    public boolean choHoanUng(Integer id) {

        return chiTietCongTacClient.choHoanUng(id);
    }

    public boolean daHoanUng(Integer id) {

        return chiTietCongTacClient.daHoanUng(id);
    }

    public boolean daTaoChiPhi(Integer id) {

        return chiTietCongTacClient.daTaoChiPhi(id);
    }

    public boolean duyet(Integer id) {

        return chiTietCongTacClient.duyet(id);
    }

    public boolean khongDuyet(Integer id) {

        return chiTietCongTacClient.khongDuyet(id);
    }

    public boolean saveCongTac(CongTacDTO congTacDTO) {

        return congTacClient.addCongTac(congTacDTO);
    }
}
