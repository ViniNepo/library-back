package com.senac.library.api.model.entities;

import com.senac.library.api.enuns.TypeContactEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    private Customer customer;

}
