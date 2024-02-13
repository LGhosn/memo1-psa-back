package com.example.support_management.controller;

import com.example.support_management.model.Customer;
import com.example.support_management.model.dto.TicketDTO;
import com.example.support_management.repository.CustomerRepository;
import com.example.support_management.repository.relations.CustomerTicketRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerTicketRepository customerTicketRepository;

    public CustomerController(
            CustomerRepository customerRepository,
            CustomerTicketRepository customerTicketRepository
    ) {
        this.customerRepository = customerRepository;
        this.customerTicketRepository = customerTicketRepository;
    }

    @GetMapping("/")
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{idCustomer}/tickets")
    public Collection<TicketDTO> getTicketsByCustomer(@PathVariable Integer idCustomer) {
        return customerTicketRepository.getTicketsByCustomer(idCustomer);
    }

    @GetMapping("/externalApiCustomers")
    public String getExternalApiCustomers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes",
                String.class);

        return response.getBody();
    }
}
