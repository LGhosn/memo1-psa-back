package com.example.support_management.controller;

import com.example.support_management.model.Version;
import com.example.support_management.model.dto.TicketDTO;
import com.example.support_management.repository.VersionRepository;
import com.example.support_management.repository.relations.VersionProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/versions")
public class VersionController {
    private final VersionRepository versionRepository;
    private final VersionProductRepository versionProductRepository;

    public VersionController(
            VersionRepository versionRepository,
            VersionProductRepository versionProductRepository) {
        this.versionRepository = versionRepository;
        this.versionProductRepository = versionProductRepository;
    }

    @GetMapping("/")
    public Collection<Version> getVersions() {
        return versionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Version> getVersionById(@RequestBody @PathVariable Integer id) { return versionRepository.findById(id); }

    @GetMapping("/{idVersion}/products/{idProduct}/tickets")
    public Collection<TicketDTO> getTicketsByVersionAndProduct(@PathVariable Integer idVersion, @PathVariable Integer idProduct) {
        return versionProductRepository.findTicketsByVersionIdAndProductId(idVersion, idProduct);
    }

    /*@GetMapping("/{productId}/tickets")
    public Collection<TicketDTO> getTicketsByProduct(@PathVariable Integer productId) {
        log.info("STARTING OPERATION: GETTING ALL TICKETS BY PRODUCTS.");
        return versionProductRepository.selectTicketsWhereIdProductEqualsTo(productId);
    }*/
}
