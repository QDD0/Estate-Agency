package org.example.estateagency.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "saleshistory")
@Data
public class SalesHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_history;

    @Column(name = "sale_date")
    private LocalDate sale_date;

    @Column(name = "price")
    private Double price;

    @Column(name = "paymenttype")
    private String paymenttype;

    @ManyToOne()
    @JoinColumn(name = "propertyid")
    private Properties property;

    @ManyToOne()
    @JoinColumn(name = "buyerid")
    private Person buyer;

    @ManyToOne()
    @JoinColumn(name = "realtorid")
    private Realtors realtor;
}
