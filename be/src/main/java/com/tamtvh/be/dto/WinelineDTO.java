package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class WinelineDTO {
    @JsonProperty("MADONG")
    private String MADONG;

    @JsonProperty("TENDONG")
    private String TENDONG;

    @JsonProperty("TRANGTHAI")
    private String TRANGTHAI;

    @JsonProperty("HINHANH")
    private String HINHANH;

    @JsonProperty("MOTA")
    private String MOTA;

    @JsonProperty("CHITIET")
    private String CHITIET;

    @JsonProperty("SOLUONGTON")
    private Integer SOLUONGTON;

    @JsonProperty("MALOAI")
    private String MALOAI;

    @JsonProperty("MATH")
    private String MATH;

    private List<ReviewDTO> reviews;

    private List<Ct_phieudatDTO> ct_phieudats;

    private List<ChangePriceDTO> changeprices;

    private TrademarkDTO trademark;

    private WinetypeDTO winetype;

    private List<Ct_promotionDTO> ct_khuyenmais;

    private List<Ct_phieunhapDTO> ct_phieunhaps;

    private List<Ct_orderDTO> ct_orders;

    private List<CungcapDTO> cungcaps;

}
