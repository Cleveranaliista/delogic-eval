package com.example.delogic_eval.controller;

import com.example.delogic_eval.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private com.example.delogic_eval.repository.EventRepository eventRepository;

    @GetMapping("/events")
    public List<com.example.delogic_eval.entity.Event> getAllEvents() {
        // Retorna todos os eventos
        return eventRepository.findAll();
    }

    @GetMapping("/tickets")
    public List<com.example.delogic_eval.entity.Ticket> getPromotionalTickets(@RequestParam String date,
                                                                              @RequestParam(required = false) Long categoryId,
                                                                              @RequestParam(required = false) String city) {

        // Converter a string 'date' para LocalDate (ex.: "2025-01-01")
        LocalDate contextDate = LocalDate.parse(date);

        // Chama o serviço de promoção com filtros
        return promotionService.getPromotionalTickets(contextDate, categoryId, city);
    }
}
