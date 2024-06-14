package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tamtvh.be.model.Promotion;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Ct_promotionDTO {
    @JsonProperty("MAKM")
    private String MAKM;

    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("PHANTRAMGIAM")
    private Integer PHANTRAMGIAM;

    private PromotionDTO promotion;

    private WinelineDTO wineline;

}
