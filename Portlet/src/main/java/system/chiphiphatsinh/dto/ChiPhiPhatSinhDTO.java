package system.chiphiphatsinh.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import system.nhanvien.dto.NhanVienDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ChiPhiPhatSinhDTO {

    private Integer id;
    @NotNull(message = "Thiếu tên chi phí phát sinh!")
    private String tenChiPhi;
    @NotNull(message = "Thiếu tháng chi phí phát sinh!")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-yyyy")
    private Date thang;
    @NotNull(message = "Thiếu nội dung!")
    private String noiDung;
    @NotNull(message = "Thiếu tiền!")
    private double soTien;

    private NhanVienDTO nhanVien;
}
