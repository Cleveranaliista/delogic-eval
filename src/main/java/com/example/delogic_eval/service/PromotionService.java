package com.example.delogic_eval.service;

import com.example.delogic_eval.repository.EventRepository;
import com.example.delogic_eval.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Formato de data para conversão de Strings
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Retorna ingressos "promocionais" (por exemplo, ainda não vendidos)
     * com base na data de hoje ou data de contexto, categoriaId e cidade.
     */
    public List<com.example.delogic_eval.entity.Ticket> getPromotionalTickets(LocalDate contextDate, Long categoryId, String city) {
          List<com.example.delogic_eval.entity.Ticket> unsoldTickets = ticketRepository.findBySoldFalse();

        return unsoldTickets.stream()
                .filter(ticket -> {
                    com.example.delogic_eval.entity.Event event = ticket.getEvent();

                    LocalDate eventDate;
                    try {
                        eventDate = LocalDate.parse(event.getEventDate().toString(), FORMATTER);
                    } catch (Exception e) {
                      return false;
                    }

                    if (categoryId != null && !categoryId.equals(event.getCategoryId())) {
                        return false;
                    }

                    if (city != null && !city.isBlank() && !city.equalsIgnoreCase(event.getCity())) {
                        return false;
                    }

                    return !eventDate.isBefore(contextDate);
                })
                .collect(Collectors.toList());
    }
}
