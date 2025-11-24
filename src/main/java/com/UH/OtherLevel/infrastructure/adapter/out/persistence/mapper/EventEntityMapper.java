package com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper;


import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {VenueEntityMapper.class}
)
public interface EventEntityMapper {


    @Mapping(target = "venueEntity", source = "venue")
    EventEntity toEntity(Event event);

    @Mapping(target = "venue", source = "venueEntity")
    Event toModel(EventEntity entity);

    List<EventEntity> toEntityList(List<Event> events);

    List<Event> toModelList(List<EventEntity> entities);
}
