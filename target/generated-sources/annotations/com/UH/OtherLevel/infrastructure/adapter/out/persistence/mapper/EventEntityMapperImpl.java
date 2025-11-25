package com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper;

import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T07:41:38-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class EventEntityMapperImpl implements EventEntityMapper {

    @Autowired
    private VenueEntityMapper venueEntityMapper;

    @Override
    public EventEntity toEntity(Event event) {
        if ( event == null ) {
            return null;
        }

        EventEntity eventEntity = new EventEntity();

        eventEntity.setVenue( venueEntityMapper.toEntity( event.getVenue() ) );
        eventEntity.setId( event.getId() );
        eventEntity.setName( event.getName() );
        eventEntity.setDescription( event.getDescription() );
        eventEntity.setDate( event.getDate() );

        return eventEntity;
    }

    @Override
    public Event toModel(EventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Event event = new Event();

        event.setVenue( venueEntityMapper.toDomain( entity.getVenue() ) );
        event.setDescription( entity.getDescription() );
        event.setId( entity.getId() );
        event.setDate( entity.getDate() );
        event.setName( entity.getName() );

        return event;
    }

    @Override
    public List<EventEntity> toEntityList(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventEntity> list = new ArrayList<EventEntity>( events.size() );
        for ( Event event : events ) {
            list.add( toEntity( event ) );
        }

        return list;
    }

    @Override
    public List<Event> toModelList(List<EventEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( entities.size() );
        for ( EventEntity eventEntity : entities ) {
            list.add( toModel( eventEntity ) );
        }

        return list;
    }
}
