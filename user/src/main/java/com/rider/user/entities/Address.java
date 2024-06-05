package com.rider.user.entities;
import jakarta.persistence.*;
import org.aspectj.weaver.ast.Var;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Profile profile;

    public UUID getId() {
        return this.id;
    }


    public String getNeighborhood() {
        return this.neighborhood;
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCEP() {
        return this.cep;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
