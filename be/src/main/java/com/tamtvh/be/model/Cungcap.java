package com.tamtvh.be.model;

import com.tamtvh.be.model.key.CungcapId;
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
@IdClass(CungcapId.class)
@Table(name="cungcap")
@ToString
@ApiModel(value = "All details about the provide.")
@Accessors(chain = true)
public class Cungcap {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANCC")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Provider provider;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wineline wineline;

    @NotBlank(message = "Price is required")
    @Column(name = "GIA", nullable = false)
    private Float GIA;

}
