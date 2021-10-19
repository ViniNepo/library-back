package com.senac.library.api.model.entities;

import com.senac.library.api.enuns.TypeContactEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private TypeContactEnum type;

    @Column
    private String number;

    @Column
    private LocalDate createDt;

    @Column
    private LocalDate updatedDt;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

}
