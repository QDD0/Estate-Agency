package org.example.estateagency.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "agencies")
public class Agencies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_agency;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "ownerid")
    private Person owner;
}
