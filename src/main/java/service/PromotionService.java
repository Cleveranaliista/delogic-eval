package com.example.delogic_eval.service;

import com.example.delogic_eval.entity.Event;
import com.example.delogic_eval.entity.Ticket;
import com.example.delogic_eval.repository.EventRepository;
import com.example.delogic_eval.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Retorna ingressos "promocionais" (por exemplo, ainda não vendidos)
     * com base na data de hoje ou data de contexto, categoriaId e cidade.
     */
    public List<Ticket> getPromotionalTickets(LocalDate contextDate, Long categoryId, String city) {
        // Buscar tickets não vendidos
        List<Ticket> unsoldTickets = ticketRepository.findBySoldFalse();

        // Filtrar baseando-se em evento e parâmetros
        return unsoldTickets.stream()
                .filter(ticket -> {
                    Event event = ticket.getEvent();

                    // Filtra por categoria, se informado
                    if (categoryId != null && !categoryId.equals(event.getCategoryId())) {
                        return false;
                    }

                    // Filtra por cidade, se informado
                    if (city != null && !city.isBlank() && !city.equalsIgnoreCase(event.getCity())) {
                        return false;
                    }

                    // Filtra se a data do evento é maior ou igual à data fornecida (contextDate)
                    LocalDate eventDate = event.getEventDate();
                    return !eventDate.isBefore(contextDate);
                })
                .collect(Collectors.toList());
    }
}