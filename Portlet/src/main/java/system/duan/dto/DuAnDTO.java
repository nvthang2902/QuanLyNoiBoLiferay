package system.duan.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import system.nhanvien.dto.NhanVienDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DuAnDTO {

    private Integer id;
    @NotNull(message = "Mời bạn nhập tên dự án")
    private String tenDuAn;
    @NotNull(message = "Ngày bắt đầu phải nhỏ hơn ngày kết thúc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayBatDau;
    @NotNull(message = "Ngày kết thúc phải lớn hơn ngày bắt đầu")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayKetThuc;
    @NotNull
    private TrangThaiDuAn trangThai;
    private NhanVienDTO nhanVien;
}
