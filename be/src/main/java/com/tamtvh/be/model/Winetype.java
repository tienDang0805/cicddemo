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
@Table(name="loairuou")
@ToString
@ApiModel(value = "All details about the wine types.")
@Accessors(chain = true)
public class Winetype {

    @Id
    @Column(name = "MALOAI", nullable = false)
    private String MALOAI;

    @Column(name = "TENLOAI")
    private String TENLOAI;

    @OneToMany(mappedBy = "winetype", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Wineline> winelines;


}
