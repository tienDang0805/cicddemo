package com.tamtvh.be.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class CtpdFromTo {
    @JsonProperty("so_luong_ban")
    Integer soLuongBan;

    @JsonProperty("MADONG")
    String MADONG;

    @JsonProperty("TENDONG")
    String TENDONG;

    @JsonProperty("HINHANH")
    String HINHANH;

    public CtpdFromTo(Object[] object) {
        this.soLuongBan = Integer.parseInt(object[0].toString());
        this.MADONG = (object[1].toString());
        this.TENDONG = (object[2].toString());
        this.HINHANH = (object[3].toString());
    }
}
