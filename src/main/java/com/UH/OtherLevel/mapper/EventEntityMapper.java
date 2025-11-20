package com.UH.OtherLevel.mapper;

import com.UH.OtherLevel.entities.EventEntity;
import com.UH.OtherLevel.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {VenueEntityMapper.class})
public interface EventEntityMapper {

    EventEntityMapper INSTANCE = Mappers.getMapper(EventEntityMapper.class);

    @Mapping(target = "venueEntity", source = "venue")
    EventEntity toEntity(Event event);

    @Mapping(target = "venue", source = "venueEntity")
    Event toModel(EventEntity entity);

    List<EventEntity> toEntityList(List<Event> events);

    List<Event> toModelList(List<EventEntity> entities);
}
