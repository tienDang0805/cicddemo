package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;


import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PromotionDTO {
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

    private StaffDTO staff;

    private List<Ct_promotionDTO> ct_khuyenmais;

}
