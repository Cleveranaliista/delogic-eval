package com.example.delogic_eval.service;

import com.example.delogic_eval.entity.Event;
import com.example.delogic_eval.entity.Ticket;
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
    public List<Ticket> getPromotionalTickets(LocalDate contextDate, Long categoryId, String city) {
        // Buscar tickets não vendidos
        List<Ticket> unsoldTickets = ticketRepository.findBySoldFalse();

        // Filtrar baseando-se em evento e parâmetros
        return unsoldTickets.stream()
                .filter(ticket -> {
                    Event event = ticket.getEvent();

                    // Converte a data do evento, se ela for armazenada como String
                    LocalDate eventDate;
                    try {
                        eventDate = LocalDate.parse(event.getEventDate().toString(), FORMATTER);
                    } catch (Exception e) {
                        // Handle parsing errors ou filtre eventos inválidos
                        return false;
                    }

                    // Filtra por categoria
                    if (categoryId != null && !categoryId.equals(event.getCategoryId())) {
                        return false;
                    }

                    // Filtra por cidade
                    if (city != null && !city.isBlank() && !city.equalsIgnoreCase(event.getCity())) {
                        return false;
                    }

                    // Filtra pela data do evento
                    return !eventDate.isBefore(contextDate);
                })
                .collect(Collectors.toList());
    }
}
