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
@Table(name="dondathang")
@ToString
@ApiModel(value = "All details about the users.")
@Accessors(chain = true)
public class Order {

    @Id
    @Column(name = "MADDH", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String MADDH;

    @Column(name = "NGAYDAT")
    private Date NGAYDAT;

//    @NotBlank(message = "Staff id is required")
//    @Column(name = "MANV", nullable = false)
//    private String MANV;

//    @NotBlank(message = "Provider id is required")
//    @Column(name = "MANCC", nullable = false)
//    private String MANCC;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANCC")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Provider provider;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Ct_order> ct_orders;



}
