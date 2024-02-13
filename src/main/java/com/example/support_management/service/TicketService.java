package com.example.support_management.service;
import com.example.support_management.enums.Priority;
import com.example.support_management.enums.Severity;
import com.example.support_management.enums.State;
import com.example.support_management.model.*;
import com.example.support_management.model.relations.CustomerTicket;
import com.example.support_management.model.relations.EmployeeTicket;
import com.example.support_management.model.relations.TicketVersion;
import com.example.support_management.model.relations.VersionProduct;
import com.example.support_management.repository.*;
import com.example.support_management.repository.relations.CustomerTicketRepository;
import com.example.support_management.repository.relations.EmployeeTicketRepository;
import com.example.support_management.repository.relations.TicketVersionRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeTicketRepository employeeTicketRepository;
    private final CustomerTicketRepository customerTicketRepository;
    private final TicketVersionRepository ticketVersionRepository;

    public TicketService(
            /* Repositorios de entidades */
            TicketRepository ticketRepository,
            CustomerRepository customerRepository,
            EmployeeRepository employeeRepository,

            /* Repositorios de relaciones entre entidades */
            EmployeeTicketRepository employeeTicketRepository,
            CustomerTicketRepository customerTicketRepository,
            TicketVersionRepository ticketVersionRepository
        ) {
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.employeeTicketRepository = employeeTicketRepository;
        this.customerTicketRepository = customerTicketRepository;
        this.ticketVersionRepository = ticketVersionRepository;
    }

    public Ticket createTicket(Ticket ticket, Customer customer, Integer idVersion, Integer idProduct) throws IllegalStateException {
        Ticket t;
        Customer c;

        /* Corroboramos que el ticket que estamos por crear sea para la versión de un producto existente */
        Optional<VersionProduct> versionAndProductRelation = ticketVersionRepository.findByIdVersionAndIdProduct(idVersion, idProduct);
        if (versionAndProductRelation.isEmpty()) {
            throw new IllegalStateException("No existe relación entre esa versión y ese producto.");
        }

        /* Guardamos la relación entre el ticket y el cliente */
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        if (optionalCustomer.isPresent()) {
            t = ticketRepository.save(ticket);
            customerTicketRepository.save(new CustomerTicket(customer.getId(), ticket.getId()));
        } else {
            t = ticketRepository.save(ticket);
            c = Customer.builder().id(customer.getId()).rs(customer.getRs()).cuit(customer.getCuit()).build();
            customerRepository.save(c);
            customerTicketRepository.save(new CustomerTicket(c.getId(), ticket.getId()));
        }

        /* Guardamos la relación entre el ticket y la version */
        ticketVersionRepository.save(new TicketVersion(t.id, idVersion));

        return t;
    }

    public Collection<Employee> assignResponsible(Collection<Employee> responsibles, Integer idTicket) {
        for (Employee r: responsibles) {
            Integer legajo = r.getLegajo();
            String firstName = r.getFirstName();
            String lastName = r.getLastName();

            Optional<Employee> optionalEmployee = employeeRepository.findById(legajo);

            if (optionalEmployee.isPresent()) {
                employeeTicketRepository.save(new EmployeeTicket(optionalEmployee.get().getLegajo(), idTicket));
            } else {
                Employee e = Employee.builder().legajo(legajo).firstName(firstName).lastName(lastName).build();
                employeeRepository.save(e);
                employeeTicketRepository.save(new EmployeeTicket(e.getLegajo(), idTicket));
            }
        }
        return responsibles;
    }

    public ResponseEntity<Object> updateTicketPriority(Integer id, Priority newPriority) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            if (!ticket.getPriority().equals(newPriority)) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("access-control-allow-origin", "*");
                ticket.setPriority(newPriority);
                ticketRepository.save(ticket);
                return new ResponseEntity<>(ticket, responseHeaders, 200);
            }
        }

        return ResponseEntity.badRequest().body("Error while updating ticket priority.");
    }

    public ResponseEntity<Object> updateTicketState(Integer id, State newState) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            if (ticket.getState().equals(State.SOLVED)) {
                return ResponseEntity.badRequest().body("Error while updating ticket state, this ticket has already been solved.");
            }

            if (!ticket.getState().equals(newState)) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("access-control-allow-origin", "*");
                ticket.setState(newState);
                if (newState.equals(State.SOLVED)) {
                    ticket.setCloseDate(LocalDateTime.now());
                }
                ticketRepository.save(ticket);
                return new ResponseEntity<>(ticket, responseHeaders, 200);
            }
        }

        return ResponseEntity.badRequest().body("Error while updating ticket state.");
    }

    public ResponseEntity<Object> updateTicketSeverity(Integer id, Severity newSeverity) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            if (!ticket.getSeverity().equals(newSeverity)) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("access-control-allow-origin", "*");
                ticket.setSeverity(newSeverity);
                ticketRepository.save(ticket);
                return new ResponseEntity<>(ticket, responseHeaders, 200);
            }
        }

        return ResponseEntity.badRequest().body("Error while updating ticket severity.");
    }

    public ResponseEntity<Object> updateTicketFields(Integer id, Severity newSeverity, Priority newPriority, State newState) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("access-control-allow-origin", "*");

            if (!ticket.getState().equals(newState)) {

                if (ticket.getState().equals(State.SOLVED)) {
                    return ResponseEntity.badRequest().body("Error while updating ticket state, this ticket has already been solved.");
                }
                if (newState.equals(State.SOLVED)) {
                    ticket.setCloseDate(LocalDateTime.now());
                }
                ticket.setState(newState);
            }

            if (!ticket.getSeverity().equals(newSeverity)) { ticket.setSeverity(newSeverity); }
            if (!ticket.getPriority().equals(newPriority)) { ticket.setPriority(newPriority); }

            ticketRepository.save(ticket);
            return new ResponseEntity<>(ticket, responseHeaders, 200);
        }

        return ResponseEntity.badRequest().body("Error while updating ticket fields.");
    }

    public Collection<Employee> getResponsibleById(Integer idTicket) {
        return employeeTicketRepository.getResponsibleByTicket(idTicket);
    }

    public Optional<Customer> getCustomerById(Integer idTicket) {
        return customerTicketRepository.getCustomerByTicket(idTicket);
    }

    public Collection<Ticket> getTickets() { return (Collection<Ticket>) ticketRepository.findAll(); }

    public Optional<Ticket> findById(Integer id) { return ticketRepository.findById(id); }

    public Collection<Ticket> findByTitleLike(String title) { return ticketRepository.findByTitleLike(title); }

    public void deleteById(Integer id) { ticketRepository.deleteById(id); }
}