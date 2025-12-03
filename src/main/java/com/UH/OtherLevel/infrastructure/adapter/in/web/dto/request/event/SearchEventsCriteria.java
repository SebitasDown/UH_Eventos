package com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SearchEventsCriteria {
    private final Long venueId;
    private final LocalDateTime dateFrom;
    private final String nameContains;

}
