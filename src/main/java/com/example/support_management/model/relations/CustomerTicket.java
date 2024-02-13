package com.example.support_management.model.relations;

import jakarta.persistence.*;

@Entity
@Table(name = "CUSTOMER_TICKET")
public class CustomerTicket {
    @Id
    @Column(name = "ID_CUSTOMER_TICKET")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "ID_CUSTOMER")
    public Integer id_customer;
    @Column(name = "ID_TICKET")
    public Integer id_ticket;

    public CustomerTicket(Integer idCustomer, Integer idTicket) {
        this.id_customer = idCustomer;
        this.id_ticket = idTicket;
    }

    public CustomerTicket() { }
}
