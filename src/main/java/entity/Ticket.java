package com.example.delogic_eval.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    private Boolean sold;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }
}
