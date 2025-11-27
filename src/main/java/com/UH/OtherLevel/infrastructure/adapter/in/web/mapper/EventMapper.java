package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;


import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.SearchEvent;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.CreateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.SearchEventsCriteria;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.UpdateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.event.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue", source = "venueId", qualifiedByName = "idToVenue")
    Event toModel(CreateEventRequest request);

    @Mapping(target = "venue", source = "venueId", qualifiedByName = "idToVenue")
    Event toUpdateModel(UpdateEventRequest request);

    @Mapping(target = "venueId", source = "venueId")
    @Mapping(target = "dateFrom", source = "dateFrom")
    @Mapping(target = "nameContains", source = "nameContains")
    SearchEvent toModel(SearchEventsCriteria criteria);

    @Mapping(target = "venueId", source = "venue.id")
    EventResponse toResponse(Event event);

    List<EventResponse> toResponseList(List<Event> events);


    @Named("idToVenue")
    default Venue idToVenue(Long id) {
        if (id == null) return null;


        Venue venue = new Venue();
        venue.setId(id);

        return venue;
    }
}
