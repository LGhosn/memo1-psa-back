package com.example.support_management.controller;

import com.example.support_management.model.Employee;
import com.example.support_management.model.Ticket;
import com.example.support_management.model.dto.TicketDTO;
import com.example.support_management.repository.EmployeeRepository;
import com.example.support_management.repository.relations.EmployeeTicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTicketRepository employeeTicketRepository;

    public EmployeeController(
            EmployeeRepository employeeRepository,
            EmployeeTicketRepository employeeTicketRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeTicketRepository = employeeTicketRepository;
    }

    @GetMapping("/")
    public Collection<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{idEmployee}/tickets")
    public Collection<TicketDTO> getTicketsByEmployee(@PathVariable Integer idEmployee) {
        return employeeTicketRepository.getTicketsByEmployee(idEmployee);
    }

    @GetMapping("/externalApiEmployees")
    public String getExternalApiCustomers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos",
                String.class);

        return response.getBody();
    }
}
