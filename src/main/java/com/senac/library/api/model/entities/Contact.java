package com.senac.library.api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.library.api.enuns.TypeContactEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private TypeContactEnum type;

    @Column
    private String number;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "contacts")
    private List<Customer> customerList;

    public Contact() {
    }

    public Contact(Long id, TypeContactEnum type, String number) {
        this.id = id;
        this.type = type;
        this.number = number;
    }
}
