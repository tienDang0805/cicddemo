package com.tamtvh.be.model;

import com.tamtvh.be.model.key.Ct_phieutraId;
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
@IdClass(Ct_phieutraId.class)
@Table(name="ct_phieutra")
@ToString
@ApiModel(value = "All details about the ct_pts.")
@Accessors(chain = true)
public class Ct_phieutra {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPT")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phieutra phieutra;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IDCTPD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Ct_phieudat ct_phieudat;

    @Column(name = "SOLUONG", nullable = false)
    private Integer SOLUONG;


}
