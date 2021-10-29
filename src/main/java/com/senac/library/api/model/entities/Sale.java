package com.senac.library.api.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Sale {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Generated(GenerationTime.INSERT)
    @Column(name = "CREATED_AT")
    private LocalDateTime createDate;

    @Column
    private Boolean active;

    @Column
    private Double total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "id.sale")
    private Set<CartItem> cartItems = new HashSet<>();

    public Sale() {
    }

    public Sale(Long id, Boolean active, Double total) {
        this.id = id;
        this.active = active;
        this.total = total;
    }
}
