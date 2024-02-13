package com.example.support_management.controller;

import com.example.support_management.model.Product;
import com.example.support_management.model.Version;
import com.example.support_management.model.dto.TicketDTO;
import com.example.support_management.repository.ProductRepository;
import com.example.support_management.repository.relations.VersionProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final VersionProductRepository versionProductRepository;

    public ProductController(
            ProductRepository productRepository,
            VersionProductRepository versionProductRepository
        ) {
        this.productRepository = productRepository;
        this.versionProductRepository = versionProductRepository;
    }

    @GetMapping("/")
    public Collection<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@RequestBody @PathVariable Integer id) { return productRepository.findById(id); }

    @GetMapping("/{id}/availableVersions")
    public Collection<Version> getProductAvailableVersions(@RequestBody @PathVariable Integer id) { return versionProductRepository.findAvailableVersionsByProductId(id); }

    /*@GetMapping("/{productId}/tickets")
    public Collection<TicketDTO> getTicketsByProduct(@PathVariable Integer productId) {
        log.info("STARTING OPERATION: GETTING ALL TICKETS BY PRODUCTS.");
        return versionProductRepository.selectTicketsWhereIdProductEqualsTo(productId);
    }*/

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }
}
