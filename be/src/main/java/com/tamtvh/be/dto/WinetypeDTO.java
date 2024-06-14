package com.tamtvh.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.catalina.LifecycleState;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class WinetypeDTO {
    @JsonProperty("MALOAI")
    private String MALOAI;

    @JsonProperty("TENLOAI")
    private String TENLOAI;

    private List<WinelineDTO> winelines;

}
