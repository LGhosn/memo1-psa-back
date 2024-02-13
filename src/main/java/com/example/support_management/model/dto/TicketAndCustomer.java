package com.example.support_management.model.dto;

import com.example.support_management.model.Customer;
import com.example.support_management.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketAndCustomer {
    public Ticket ticket;
    public Customer customer;
}
