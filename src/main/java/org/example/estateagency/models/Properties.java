package org.example.estateagency.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "properties")
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_property;

    @Column(name = "country")
    @NotBlank(message = "Заполните поле 'Страна'")
    private String country;

    @Column(name = "city")
    @NotBlank(message = "Заполните поле 'Город'")
    private String city;

    @Column(name = "typehouse")
    @NotBlank(message = "Заполните поле 'Тип дома'")
    private String typehouse;

    @Column(name = "floorcount")
    @NotNull(message = "Заполните поле 'Количество этажей'")
    @Min(value = 1, message = "Количество этажей должно быть не менее 1")
    private Integer floorcount;

    @Column(name = "roomcount")
    @NotNull(message = "Заполните поле 'Количество комнат'")
    @Min(value = 1, message = "Количество комнат должно быть не менее 1")
    private Integer roomcount;

    @ManyToOne()
    @JoinColumn(name = "ownerid")
    @NotNull(message = "Выберите владельца")
    private Person owner;
}