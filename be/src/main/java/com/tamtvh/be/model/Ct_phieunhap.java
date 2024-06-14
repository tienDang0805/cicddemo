package com.tamtvh.be.model;

import com.tamtvh.be.model.key.Ct_phieunhapId;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(Ct_phieunhapId.class)
@Table(name="ct_phieunhap")
@ToString
@ApiModel(value = "All details about the ct_pns.")
@Accessors(chain = true)
public class Ct_phieunhap {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPN")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phieunhap phieunhap;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wineline wineline;

    @NotBlank(message = "Quantity is required")
    @Column(name = "SOLUONG", nullable = false)
    private Integer SOLUONG;

    @NotBlank(message = "Price is required")
    @Column(name = "GIA", nullable = false)
    private Float GIA;


}
