package com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue;

import lombok.Data;

@Data
public class CreateVenueRequest {
    private String name;
    private String address;
    private Integer capacity;
}
