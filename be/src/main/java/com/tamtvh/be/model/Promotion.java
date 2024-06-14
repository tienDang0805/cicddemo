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
@Table(name="khuyenmai")
@ToString
@ApiModel(value = "All details about the users.")
@Accessors(chain = true)
public class Promotion {

    @Id
    @Column(name = "MAKM", nullable = false)
    private String MAKM;

    @Column(name = "TENKM")
    private String TENKM;

    @Column(name = "NGAYBATDAU")
    private Date NGAYBATDAU;

    @Column(name = "NGAYKETTHUC")
    private Date NGAYKETTHUC;

    @Column(name = "LIDO")
    private String  LIDO;

//    @NotBlank(message = "Staff id is required")
//    @Column(name = "MANV", nullable = false)
//    private String MANV;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_promotion> ct_promotions;


}
