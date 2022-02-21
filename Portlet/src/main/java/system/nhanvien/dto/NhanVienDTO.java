package system.nhanvien.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
public class NhanVienDTO {
    private Integer id;
    @NotNull(message = "Họ tên không được để trống!")
    private String tenNhanVien;

    @NotNull(message = "Ngày sinh không được để trống!")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date ngaySinh;

    private boolean gioiTinh;
    @NotNull(message = "Thiếu lương!")
    private double luongChinhThuc;
    @NotNull(message = "Thiếu số điện thoại!")
    private String sdt;
    @NotNull(message = "Thiếu thuế!")
    private double thueTNCN;
    private long userId;


}
