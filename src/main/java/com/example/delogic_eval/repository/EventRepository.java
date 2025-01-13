package com.example.delogic_eval.repository;

import com.example.delogic_eval.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCity(String city);
    List<Event> findByEventDateAfter(LocalDate date);
}
