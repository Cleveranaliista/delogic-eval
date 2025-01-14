package com.example.delogic_eval.controller;

import com.example.delogic_eval.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private com.example.delogic_eval.repository.EventRepository eventRepository;

    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents() {
        try {
            List<com.example.delogic_eval.entity.Event> events = eventRepository.findAll();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar eventos. Por favor, tente novamente mais tarde.");
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<?> getPromotionalTickets(@RequestParam(required = false) String date,
                                                   @RequestParam(required = false) Long categoryId,
                                                   @RequestParam(required = false) String city) {
        try {
            LocalDate contextDate;
            try {
                contextDate = LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("O parâmetro 'date' deve estar no formato válido 'yyyy-MM-dd'.");
            }

            List<com.example.delogic_eval.entity.Ticket> tickets = promotionService.getPromotionalTickets(contextDate, categoryId, city);

            // Verifica se a lista está vazia
            if (tickets.isEmpty()) {
                String message = String.format(
                        "Nenhum ticket promocional encontrado para os parâmetros: data='%s', categoria='%s', cidade='%s'.",
                        date,
                        categoryId != null ? categoryId : "não especificada",
                        city != null ? city : "não especificada"
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }

            // Retorna os tickets encontrados
            return ResponseEntity.ok(tickets);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a requisição. Por favor, tente novamente mais tarde.");
        }
    }
}
