package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;


import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.CreateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.CreateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.UpdateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.venue.VenueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VanueMapper {

    // Response
    VenueResponse toResponse(Venue venue);
    List<VenueResponse> toResponseList(List<Venue> venues);

    // CREATE — nunca se pasa el ID
    @Mapping(target = "id", ignore = true)
    Venue toModel(CreateVenueRequest request);

    // UPDATE — aquí sí
    @Mapping(target = "id", source = "id")
    Venue toUpdateModel(UpdateVenueRequest request);
}

