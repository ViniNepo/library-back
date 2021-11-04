package com.senac.library.api.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String complement;

    @Column
    private String state;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String zip;

    @Column
    private Boolean principal;

    @JsonIgnore
    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customerList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy =  "address")
    private List<Sale> saleList = new ArrayList<>();

    public Address() {
    }

    public Address(Long id, String street, String number, String complement, String state, String city, String country, String zip, Boolean principal) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.state = state;
        this.city = city;
        this.country = country;
        this.zip = zip;
        this.principal = principal;
    }
}
