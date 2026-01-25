package org.example.estateagency.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "properties")
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_property;

    @Column(name = "country")
    @NotBlank(message = "Заполните поле")
    private String country;

    @Column(name = "city")
    @NotBlank(message = "Заполните поле")
    private String city;

    @Column(name = "typehouse")
    @NotBlank(message = "Заполните поле")
    private String typehouse;

    @Column(name = "floorcount")
    @NotBlank(message = "Заполните поле")
    private Integer floorcount;

    @Column(name = "roomcount")
    @NotBlank(message = "Заполните поле")
    private Integer roomcount;

    @ManyToOne()
    @JoinColumn(name = "ownerid")
    private Person owner;
}
