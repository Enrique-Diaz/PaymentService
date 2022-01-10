package com.aplazo.payments.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * This is the entity for Credit
 *
 * @author Enrique Diaz
 * */
@Entity
@Data
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private int id;
    private Double amount;
    private Integer term;
    private Double rate;

    @OneToMany(mappedBy = "creditRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;
}