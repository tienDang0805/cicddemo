package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.model.Wineline;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Ct_orderDTO {
    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("MADDH")
    private String MADDH;

    @JsonProperty("SOLUONG")
    private Integer SOLUONG;

    @JsonProperty("GIA")
    private Float GIA;

    private WinelineDTO wineline;

    private OrderDTO order;

}
