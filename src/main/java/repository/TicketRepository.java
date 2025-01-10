package com.example.delogic_eval.repository;

import com.example.delogic_eval.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Buscar todos os tickets ainda não vendidos
    List<Ticket> findBySoldFalse();
}
