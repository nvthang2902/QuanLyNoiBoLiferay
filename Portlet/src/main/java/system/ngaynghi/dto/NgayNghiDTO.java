package system.ngaynghi.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class NgayNghiDTO {

    private long id;

    @NotNull(message = "Nhập ngày bắt đầu nghỉ lễ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @NotNull(message = "Nhập ngày cuối nghỉ lễ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    @NotNull(message = "Tên ngày lễ")
    private String title;
    @NotBlank
    private String description;
}
