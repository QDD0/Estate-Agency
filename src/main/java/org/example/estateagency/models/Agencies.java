package org.example.estateagency.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "agencies")
public class Agencies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_agency;

    @Column(name = "name")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Column(name = "country")
    @NotBlank(message = "Страна не может быть пустой")
    private String country;

    @Column(name = "city")
    @NotBlank(message = "Город не может быть пустым")
    private String city;

    @Column(name = "phone")
    @NotBlank(message = "Заполните поле об телефоне")
    @Pattern(regexp = "^\\+?[0-9\\-\\s()]{10,20}$",
            message = "Телефон должен быть в формате +XXX-XXX-XX-XX-XX или +X XXX XXX XX XX")
    private String phone;

    @Column(name = "email")
    @NotBlank(message = "Почта не может быть пустой ")
    @Email(message = "Введите корректный email ")
    private String email;

    @ManyToOne
    @JoinColumn(name = "ownerid")
    private Person owner;

    @OneToMany(mappedBy = "agency")
    private List<Realtors> realtors;
}
