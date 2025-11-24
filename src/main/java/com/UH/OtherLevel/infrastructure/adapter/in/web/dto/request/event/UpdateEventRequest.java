package com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateEventRequest {
    private String name;
    private String description;
    private LocalDateTime date;
    private Long venueId;
}
