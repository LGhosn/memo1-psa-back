package com.example.support_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @Column(name = "ID_CUSTOMER")
    public Integer id;

    @Column(name = "RS")
    public String rs;

    @Column(name = "CUIT")
    public String cuit;
}
