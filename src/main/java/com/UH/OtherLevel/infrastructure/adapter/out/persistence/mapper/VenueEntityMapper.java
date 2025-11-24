package com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper;


import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenueEntityMapper {

    VenueEntity toEntity(Venue venue);

    Venue toDomain(VenueEntity venueEntity);

    List<VenueEntity> toEntityList(List<Venue> venueList);

    List<Venue> toModelList(List<VenueEntity> venueEntityList);
}
