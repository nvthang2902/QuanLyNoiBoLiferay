package com.example.quanlynoiboapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Lob;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class HoaDonDTO implements Serializable {

    private Integer id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
}
