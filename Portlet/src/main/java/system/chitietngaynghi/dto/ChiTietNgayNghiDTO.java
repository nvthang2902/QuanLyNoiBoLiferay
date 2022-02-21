package system.chitietngaynghi.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import system.ngaynghi.dto.NgayNghiDTO;
import system.nhanvien.dto.NhanVienDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ChiTietNgayNghiDTO {

    private Integer id;

    @NotNull(message = "Nhập ngày xin nghỉ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayXinNghi;
    @NotNull(message = "Nhập số ngày muốn nghỉ")
    private float soNgayNghi;
    private TrangThaiBuoiXinNghi buoiNghi;
    private TrangThaiNghi trangThai;
    private TinhTrangDuyet tinhTrangDuyet;
    @NotNull(message = "Nhập lý do")
    private String lyDo;

    private NhanVienDTO nhanVien;
}
