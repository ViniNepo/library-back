package com.senac.library.api.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.library.api.enuns.TypeContactEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
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
    @ManyToMany(mappedBy = "contacts")
    private List<Customer> customerList;

    public Contact() {
    }

    public Contact(Long id, TypeContactEnum type, String number) {
        this.id = id;
        this.type = type;
        this.number = number;
    }
}
