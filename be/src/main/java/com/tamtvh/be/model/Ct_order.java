package com.tamtvh.be.model;

import com.tamtvh.be.model.key.Ct_orderId;
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
@IdClass(Ct_orderId.class)
@Table(name="ct_dondathang")
@ToString
@ApiModel(value = "All details about the users.")
@Accessors(chain = true)
public class Ct_order {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wineline wineline;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADDH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;

    @Column(name = "SOLUONG")
    private Integer SOLUONG;

    @Column(name = "GIA")
    private Float GIA;


}
