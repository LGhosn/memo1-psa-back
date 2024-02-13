package com.example.support_management.repository.relations;

import com.example.support_management.model.Employee;
import com.example.support_management.model.relations.EmployeeTicket;
import com.example.support_management.model.dto.TicketDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeTicketRepository extends JpaRepository<EmployeeTicket, Integer> {
    Optional<EmployeeTicket> findById(Integer legajo);

    @Query(
            nativeQuery = false,
            value = "SELECT new com.example.support_management.model.dto.TicketDTO(t.id,t.title,t.priority,t.state,t.severity,t.type,t.creationDate,t.closeDate) FROM Ticket t INNER JOIN EmployeeTicket et on t.id = et.id_ticket WHERE et.id_employee = :idEmployee"
    )
    Collection<TicketDTO> getTicketsByEmployee(Integer idEmployee);

    @Query(
            nativeQuery = false,
            value = "SELECT new com.example.support_management.model.Employee(e.legajo, e.firstName, e.lastName) FROM Employee e INNER JOIN EmployeeTicket et on e.legajo = et.id_employee WHERE et.id_ticket = :idTicket"
    )
    Collection<Employee> getResponsibleByTicket(Integer idTicket);
}
