package com.senac.library.api.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.library.api.enuns.TypeContactEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class CreditCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String cpf;

    @Column
    private String number;

    @Column
    private String validateData;

    @Column
    private String securityNumber;

    @JsonIgnore
    @ManyToMany(mappedBy = "creditCards")
    private List<Customer> customerList;

    public CreditCard() {
    }

    public CreditCard(Long id, String name, String cpf, String number, String validateData, String securityNumber) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.number = number;
        this.validateData = validateData;
        this.securityNumber = securityNumber;
    }
}
