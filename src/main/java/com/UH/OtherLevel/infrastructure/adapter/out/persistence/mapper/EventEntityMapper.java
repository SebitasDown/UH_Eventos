package com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper;

import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {VenueEntityMapper.class}
)
public interface EventEntityMapper {


    @Mapping(target = "venue", source = "venue")
    EventEntity toEntity(Event event);


    @Mapping(target = "venue", source = "venue")
    Event toModel(EventEntity entity);

    List<EventEntity> toEntityList(List<Event> events);

    List<Event> toModelList(List<EventEntity> entities);
}
