package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;

import com.UH.OtherLevel.dto.EventDTO;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "venueId", source = "venue.id")
    EventDTO toDTO(Event event);

    @Mapping(target = "venue", source = "venueId", qualifiedByName = "idToVenue")
    Event toModel(EventDTO dto);

    List<EventDTO> toDTOList(List<Event> events);

    List<Event> toModelList(List<EventDTO> dtos);

    @Named("idToVenue")
    default Venue idToVenue(Long id) {
        if (id == null) return null;
        Venue v = new Venue();
        v.setId(id);
        return v;
    }
}
