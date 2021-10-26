package com.senac.library.api.model.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double totalValue;

    @CreatedDate
    @Column
    private LocalDate createDt;

    @LastModifiedDate
    @Column
    private LocalDate updatedDt;

    @OneToOne
    private Customer customer;

//    @OneToOne
//    private List<Book> bookList;
}
