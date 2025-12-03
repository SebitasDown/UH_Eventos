package com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Long venueId;
}
