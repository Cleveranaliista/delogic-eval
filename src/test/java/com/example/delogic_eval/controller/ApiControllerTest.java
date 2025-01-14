
package com.example.delogic_eval.controller;

import com.example.delogic_eval.entity.Ticket;
import com.example.delogic_eval.repository.EventRepository;
import com.example.delogic_eval.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromotionService promotionService;

    @MockBean
    private EventRepository eventRepository; // Adding missing dependency

    @Test
    void getPromotionalTickets_ValidParams_ReturnsTickets() throws Exception {
        // Arrange: Mock the service to return a list of tickets
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setName("Promo Ticket");
        when(promotionService.getPromotionalTickets(any(LocalDate.class), eq(1L), eq("SaoPaulo")))
                .thenReturn(List.of(ticket));

        // Act & Assert: Perform the GET request and validate the response
        mockMvc.perform(get("/api/tickets")
                        .param("date", "2025-01-01")
                        .param("categoryId", "1")
                        .param("city", "SaoPaulo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Promo Ticket"));
    }

    @Test
    void getPromotionalTickets_InvalidDate_ReturnsBadRequest() throws Exception {
        // Act & Assert: Perform the GET request with invalid date and validate the response
        mockMvc.perform(get("/api/tickets")
                        .param("date", "invalid-date")
                        .param("categoryId", "1")
                        .param("city", "SaoPaulo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
