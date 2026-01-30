package org.example.estateagency.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "realtors")
public class Realtors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_realtor;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "personid")
    private Person person;

    @ManyToOne()
    @JoinColumn(name = "agencyid")
    private Agencies agency;

    @OneToMany(mappedBy = "realtor")
    private List<SalesHistory> salesHistory;
}