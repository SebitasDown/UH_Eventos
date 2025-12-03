package com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.venue;

import lombok.Data;

@Data
public class VenueResponse {
    private Long id;
    private String name;
    private String address;
    private Integer capacity;
}
