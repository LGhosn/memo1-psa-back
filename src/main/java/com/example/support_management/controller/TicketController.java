package com.example.support_management.controller;

import com.example.support_management.enums.Priority;
import com.example.support_management.enums.Severity;
import com.example.support_management.enums.State;
import com.example.support_management.model.Customer;
import com.example.support_management.model.Employee;
import com.example.support_management.model.Ticket;
import com.example.support_management.model.dto.TicketAndCustomer;
import com.example.support_management.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }

    @GetMapping("/")
    public Collection<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/{id}")
    public Optional<Ticket> getTicket(@RequestBody @PathVariable Integer id) {
        return ticketService.findById(id);
    }

    @GetMapping("/findWithTitleLike={title}")
    public Collection<Ticket> getTicketsWithTitleLike(@PathVariable String title) {
        return ticketService.findByTitleLike(title);
    }

    @GetMapping("/{id}/responsible")
    public Collection<Employee> getResponsible(@PathVariable Integer id) {
        return ticketService.getResponsibleById(id);
    }

    @GetMapping("/{id}/customer")
    public Optional<Customer> getCustomer(@PathVariable Integer id) {
        return ticketService.getCustomerById(id);
    }

    @PostMapping("/")
    public Ticket createTicket(
            @RequestBody TicketAndCustomer ticketAndCustomerData,
            @RequestParam Integer idVersion,
            @RequestParam Integer idProduct
    ) throws Exception {
        return ticketService.createTicket(
                ticketAndCustomerData.getTicket(),
                ticketAndCustomerData.getCustomer(),
                idVersion,
                idProduct
        );
    }

    @PostMapping("/{idTicket}/assignResponsible")
    public Collection<Employee> assignResponsible(@RequestBody Collection<Employee> responsibleEmployees, @PathVariable Integer idTicket) {
        return ticketService.assignResponsible(responsibleEmployees, idTicket);
    }

    @PatchMapping("/{id}/updatePriority")
    public ResponseEntity<Object> updateTicketPriority(@PathVariable Integer id, @RequestParam Priority newPriority) {
        return ticketService.updateTicketPriority(id, newPriority);
    }

    @PatchMapping("/{id}/updateState")
    public ResponseEntity<Object> updateTicketState(@PathVariable Integer id, @RequestParam State newState) {
        return ticketService.updateTicketState(id, newState);
    }

    @PatchMapping("/{id}/updateSeverity")
    public ResponseEntity<Object> updateTicketSeverity(@PathVariable Integer id, @RequestParam Severity newSeverity) {
        return ticketService.updateTicketSeverity(id, newSeverity);
    }

    @PatchMapping("/{id}/updateFields")
    public ResponseEntity<Object> updateTicketFields(@PathVariable Integer id, @RequestParam Severity newSeverity, @RequestParam Priority newPriority, @RequestParam State newState) {
        return ticketService.updateTicketFields(id, newSeverity, newPriority, newState);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteById(id);
    }
}
