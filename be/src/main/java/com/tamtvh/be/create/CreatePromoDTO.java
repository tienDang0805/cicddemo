package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePromoDTO {
    @JsonProperty("MAKM")
    private String MAKM;

    @JsonProperty("TENKM")
    private String TENKM;

    @JsonProperty("NGAYBATDAU")
    private Date NGAYBATDAU;

    @JsonProperty("NGAYKETTHUC")
    private Date NGAYKETTHUC;

    @JsonProperty("LIDO")
    private String LIDO;

    @JsonProperty("MANV")
    private String MANV;
}
