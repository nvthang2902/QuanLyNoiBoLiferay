package system.ngaynghinhanvien.dto;

import lombok.Data;
import system.nhanvien.dto.NhanVienDTO;

@Data
public class NgayNghiNhanVienDTO {

    private Integer id;
    private Double soNgayDaNghi;
    private Double soNgayNghiPhep;
    private Double soNgayNghiTon;
    private Integer nam;

    private NhanVienDTO nhanVien;
}