package com.tamtvh.be.model;

import com.tamtvh.be.model.key.Ct_promotionId;
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
@IdClass(Ct_promotionId.class)
@Table(name="ct_khuyenmai")
@ToString
@ApiModel(value = "All details about the ct_promo.")
@Accessors(chain = true)
public class Ct_promotion {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAKM")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Promotion promotion;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wineline wineline;

    @Column(name = "PHANTRAMGIAM")
    private Integer PHANTRAMGIAM;


}
