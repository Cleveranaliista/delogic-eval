package com.example.delogic_eval.repository;

import com.example.delogic_eval.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Método para buscar eventos por categoria e cidade
    List<Event> findByCategoryIdAndCity(Long categoryId, String city);
}
