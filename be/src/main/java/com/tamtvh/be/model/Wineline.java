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
@Table(name="dongruou")
@ToString
@ApiModel(value = "All details about the winelines.")
@Accessors(chain = true)
public class Wineline {

    @Id
    @Column(name = "MADONG", nullable = false)
    private String MADONG;

    @Column(name = "TENDONG")
    private String TENDONG;

    @Column(name = "TRANGTHAI")
    private String TRANGTHAI;

    @Column(name = "HINHANH")
    private String HINHANH;

    @Column(name = "MOTA")
    private String  MOTA;

    @Column(name = "CHITIET")
    private String CHITIET;

    @Column(name = "SOLUONGTON")
    private Integer SOLUONGTON;

//    @Column(name = "MALOAI", nullable = false)
//    private String MALOAI;

//    @Column(name = "MATH", nullable = false)
//    private String MATH;

    @OneToMany(mappedBy = "wineline", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Review> reviews;

    @OneToMany(mappedBy = "wineline", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_phieudat> ct_phieudats;

    @OneToMany(mappedBy = "wineline", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ChangePrice> changeprices;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MATH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Trademark trademark;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MALOAI")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Winetype winetype;

    @OneToMany(mappedBy = "wineline", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_promotion> ct_promotions;

    @OneToMany(mappedBy = "wineline", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_phieunhap> ct_phieunhaps;

    @OneToMany(mappedBy = "wineline", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_order> ct_orders;

    @OneToMany(mappedBy = "wineline", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Cungcap> cungcaps;

}
