package system.hoadon.dto;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class HoaDonDTO {

    private Integer id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
}
