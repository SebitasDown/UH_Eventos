package com.UH.OtherLevel.domain.model;

import com.UH.OtherLevel.domain.exceptions.eventExceptions.InvalidEventDateException;
import com.UH.OtherLevel.domain.exceptions.venueExceptions.VanueCapacityExceededException;
import com.UH.OtherLevel.domain.exceptions.venueExceptions.VenueNotFoundException;

import java.time.LocalDateTime;


public class Event {
    private Long id;
    private String name;
    private LocalDateTime date;
    private Venue venue;

    public Event() {
    }

    public Event(Long id, String name, LocalDateTime date, Venue venue) {

        if (date.isBefore(LocalDateTime.now())){
            throw new InvalidEventDateException();
        }

        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;

        if (venue != null && venue.getCapacity() < 10){
            throw new VanueCapacityExceededException(venue.getCapacity());
        }
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
        if (date.isBefore(LocalDateTime.now())){
            throw new InvalidEventDateException();
        }
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
        if (venue != null && venue.getCapacity() < 10){
            throw new VanueCapacityExceededException(venue.getCapacity());
        }
        this.venue = venue;
    }
}
