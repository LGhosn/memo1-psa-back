package com.example.support_management.model.relations;

import jakarta.persistence.*;

@Entity
@Table(name = "TICKET_VERSION")
public class TicketVersion {
    @Id
    @Column(name = "ID_TICKET_VERSION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "ID_TICKET")
    public Integer id_ticket;
    @Column(name = "ID_VERSION")
    public Integer id_version;

    public TicketVersion(Integer idTicket, Integer idVersion) {
        this.id_ticket = idTicket;
        this.id_version = idVersion;
    }

    public TicketVersion() { }
}
