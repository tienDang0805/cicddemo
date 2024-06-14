package com.tamtvh.be.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ct_phieudat")
@ToString
@ApiModel(value = "All details about the users.")
@Accessors(chain = true)
public class Ct_phieudat {

    @Id
    @Column(name = "IDCTPD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDCTPD;

//    @NotBlank(message = "FName is required")
//    @Column(name = "MAPD", nullable = false)
//    private String MAPD;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Phieudat phieudat;

//    @Column(name = "MADONG")
//    private String MADONG;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wineline wineline;

    @Column(name = "SOLUONG")
    private Integer SOLUONG;

    @Column(name = "GIA")
    private Float GIA;

    @OneToMany(mappedBy = "ct_phieudat", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_phieutra> ct_phieutras;


}
