package org.example.estateagency.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_person;

    @Column(name = "firstname")
    @NotBlank(message = "Заполните поле")
    private String firstname;

    @Column(name = "surname")
    @NotBlank(message = "Заполните поле")
    private String surname;

    @Column(name = "lastname")
    @NotBlank(message = "Заполните поле")
    private String lastname;

    @Column(name = "birthdate")
    @NotNull(message = "Заполните поле")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthdate;

    @Column(name = "passportseries")
    @NotBlank(message = "Заполните поле")
    @Size(min = 4, max = 4, message = "Серия паспорта должна содержать 4 цифры")
    @Pattern(regexp = "\\d{4}", message = "Серия паспорта должна содержать только цифры")
    private String passportseries;

    @Column(name = "passportnumber")
    @NotBlank(message = "Заполните поле")
    @Size(min = 6, max = 6, message = "Номер паспорта должен содержать 6 цифр")
    @Pattern(regexp = "\\d{6}", message = "Номер паспорта должен содержать только цифры")
    private String passportnumber;

    @Column(name = "phone")
    @NotBlank(message = "Заполните поле")
    @Pattern(regexp = "^\\+?[0-9\\-\\s()]{10,20}$",
            message = "Телефон должен быть в формате +X-XXX-XXX-XX-XX или +X XXX XXX XX XX")
    private String phone;

    @Column(name = "country")
    @NotBlank(message = "Заполните поле")
    private String country;

    @Column(name = "city")
    @NotBlank(message = "Заполните поле")
    private String city;
}