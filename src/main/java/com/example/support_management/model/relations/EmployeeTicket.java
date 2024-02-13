package com.example.support_management.model.relations;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEE_TICKET")
public class EmployeeTicket {
    @Id
    @Column(name = "ID_EMPLOYEE_TICKET")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "ID_EMPLOYEE")
    public Integer id_employee;
    @Column(name = "ID_TICKET")
    public Integer id_ticket;

    public EmployeeTicket(Integer idEmployee, Integer idTicket) {
        this.id_employee = idEmployee;
        this.id_ticket = idTicket;
    }

    public EmployeeTicket() { }
}
