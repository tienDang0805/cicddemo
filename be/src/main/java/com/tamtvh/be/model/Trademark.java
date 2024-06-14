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
@Table(name="thuonghieu")
@ToString
@ApiModel(value = "All details about the brands.")
@Accessors(chain = true)
public class Trademark {

    @Id
    @Column(name = "MATH", nullable = false)
    private String MATH;

    @Column(name = "TENTH")
    private String TENTH;

    @OneToMany(mappedBy = "trademark", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Wineline> winelines;

}
