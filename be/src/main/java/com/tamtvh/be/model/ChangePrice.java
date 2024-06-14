package com.tamtvh.be.model;

import com.tamtvh.be.mapper.WinelineMapper;
import com.tamtvh.be.model.key.ChangePriceId;
import com.tamtvh.be.model.key.ReviewId;
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
@IdClass(ChangePriceId.class)
@Table(name="thaydoigia")
@ToString
@ApiModel(value = "All details about the roles.")
@Accessors(chain = true)
public class ChangePrice {

//    @Id
//    @Column(name = "MADONG", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String MADONG;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wineline wineline;

    @Id
    @Column(name = "NGAYTHAYDOI", nullable = false)
    private Date NGAYTHAYDOI;

//    @NotBlank(message = "MANV is required")
//    @Column(name = "MANV", nullable = false)
//    private String MANV;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;

    @Column(name = "GIA", nullable = false)
    private Float GIA;


}
