package com.example.delogic_eval.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonBackReference
    private Event event;

    @Column(nullable = false)
    private Boolean sold;

    @Column(nullable = false) // Adicionando o campo `name`
    private String name;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public String getName() { // Getter para `name`
        return name;
    }

    public void setName(String name) { // Setter para `name`
        this.name = name;
    }
}
