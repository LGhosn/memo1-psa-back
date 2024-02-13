package com.example.support_management.repository.relations;

import com.example.support_management.model.Customer;
import com.example.support_management.model.dto.TicketDTO;
import com.example.support_management.model.relations.CustomerTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface CustomerTicketRepository extends JpaRepository<CustomerTicket, Integer> {
    Optional<CustomerTicket> findById(Integer legajo);

    @Query(
            nativeQuery = false,
            value = "SELECT new com.example.support_management.model.dto.TicketDTO(t.id,t.title,t.priority,t.state,t.severity,t.type, t.creationDate, t.closeDate) FROM Ticket t INNER JOIN CustomerTicket ct on t.id = ct.id_ticket WHERE ct.id_customer = :idCustomer"
    )
    Collection<TicketDTO> getTicketsByCustomer(Integer idCustomer);

    @Query(
            nativeQuery = false,
            value = "SELECT new com.example.support_management.model.Customer(c.id, c.rs, c.cuit) FROM Customer c INNER JOIN CustomerTicket ct on c.id = ct.id_customer WHERE ct.id_ticket = :idTicket"
    )
    Optional<Customer> getCustomerByTicket(Integer idTicket);
}
