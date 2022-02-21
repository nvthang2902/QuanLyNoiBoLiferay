package com.example.quanlynoiboapi.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class NgayNghiDTO implements Serializable {

    private Integer id;
    private Date start;
    private Date end;
    private String title;
    private String description;
}
