package com.UH.OtherLevel.domain.model;

import com.UH.OtherLevel.domain.exceptions.eventExceptions.InvalidEventDateException;
import com.UH.OtherLevel.domain.exceptions.venueExceptions.VanueCapacityExceededException;
import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;

import java.time.LocalDateTime;


public class Event {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Venue venue;

    public Event() {
    }

    public Event(Long id, String name, String description, LocalDateTime date, Venue venue) {



        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.venue = venue;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {

        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
