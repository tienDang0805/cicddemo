package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class OrderDTO {
    @JsonProperty("MADDH")
    private String MADDH;

    @JsonProperty("NGAYDAT")
    private Date NGAYDAT;

    @JsonProperty("MANV")
    private String MANV;

    @JsonProperty("MANCC")
    private String MANCC;

    private List<Ct_orderDTO> ct_orders;

    private StaffDTO staff;

    private ProviderDTO provider;
}
