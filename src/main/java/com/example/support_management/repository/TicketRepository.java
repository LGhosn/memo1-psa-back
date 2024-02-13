package com.example.support_management.repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.support_management.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findById(Integer id);
    Collection<Ticket> findByTitleLike(String title);
}
