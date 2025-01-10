package com.example.delogic_eval.repository;

import com.example.delogic_eval.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Exemplo de método customizado
    List<Event> findByCategoryIdAndCity(Long categoryId, String city);
}
