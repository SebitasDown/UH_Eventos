package com.UH.OtherLevel.domain.model;

import java.time.LocalDateTime;

public class SearchEvent {
    private Long venueId;
    private LocalDateTime dateFrom;
    private String nameContains;

    public SearchEvent(Long venueId, LocalDateTime dateFrom, String nameContains) {
        this.venueId = venueId;
        this.dateFrom = dateFrom;
        this.nameContains = nameContains;
    }

    public Long getVenueId() { return venueId; }
    public LocalDateTime getDateFrom() { return dateFrom; }
    public String getNameContains() { return nameContains; }
}

