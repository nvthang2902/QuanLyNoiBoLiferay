package com.example.quanlynoiboapi.dto;

import com.example.quanlynoiboapi.model.TinhTrangDuyet;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class OTDTO implements Serializable {
    private Integer id;
    @NotNull(message = "Ngày bắt đầu phải nhỏ hơn ngày kết thúc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngayBatDauOT;
    @NotNull(message = "Ngày kết thúc phải lón hơn ngày bắt đầu")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngayKetThucOT;
    @Min(value=1, message="Thời gian OT phải lớn hơn 1")
    @Max(value=8, message="Thời gian OT phải bé hơn 8")
    @NotNull(message = "Thời gian OT phải phải là số")
    private Integer timeOT;
    @NotNull(message = "Lý Do không được trống")
    private String lyDo;
    private NhanVienDTO nhanVien;
    private DuAnDTO duAn;
    private TinhTrangDuyet tinhTrangDuyet;
}
