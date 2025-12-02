package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;

import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.SearchEvent;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.CreateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.SearchEventsCriteria;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.event.UpdateEventRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.event.EventResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T09:44:23-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toModel(CreateEventRequest request) {
        if ( request == null ) {
            return null;
        }

        Event event = new Event();

        event.setVenue( idToVenue( request.getVenueId() ) );
        event.setDescription( request.getDescription() );
        event.setDate( request.getDate() );
        event.setEndDate( request.getEndDate() );
        event.setName( request.getName() );

        return event;
    }

    @Override
    public Event toUpdateModel(UpdateEventRequest request) {
        if ( request == null ) {
            return null;
        }

        Event event = new Event();

        event.setVenue( idToVenue( request.getVenueId() ) );
        event.setDescription( request.getDescription() );
        event.setDate( request.getDate() );
        event.setEndDate( request.getEndDate() );
        event.setName( request.getName() );

        return event;
    }

    @Override
    public SearchEvent toModel(SearchEventsCriteria criteria) {
        if ( criteria == null ) {
            return null;
        }

        Long venueId = null;
        LocalDateTime dateFrom = null;
        String nameContains = null;

        venueId = criteria.getVenueId();
        dateFrom = criteria.getDateFrom();
        nameContains = criteria.getNameContains();

        SearchEvent searchEvent = new SearchEvent( venueId, dateFrom, nameContains );

        return searchEvent;
    }

    @Override
    public EventResponse toResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponse eventResponse = new EventResponse();

        eventResponse.setVenueId( eventVenueId( event ) );
        eventResponse.setId( event.getId() );
        eventResponse.setName( event.getName() );
        eventResponse.setDescription( event.getDescription() );
        eventResponse.setDate( event.getDate() );

        return eventResponse;
    }

    @Override
    public List<EventResponse> toResponseList(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventResponse> list = new ArrayList<EventResponse>( events.size() );
        for ( Event event : events ) {
            list.add( toResponse( event ) );
        }

        return list;
    }

    private Long eventVenueId(Event event) {
        Venue venue = event.getVenue();
        if ( venue == null ) {
            return null;
        }
        return venue.getId();
    }
}
