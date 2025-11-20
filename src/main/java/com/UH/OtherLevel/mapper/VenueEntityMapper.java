package com.UH.OtherLevel.mapper;

import com.UH.OtherLevel.entities.VenueEntity;
import com.UH.OtherLevel.model.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VenueEntityMapper {

    VenueEntityMapper INSTANCE = Mappers.getMapper(VenueEntityMapper.class);

    VenueEntity toEntity(Venue venue);

    Venue toDomain(VenueEntity venueEntity);

    List<VenueEntity> toEntityList(List<Venue> venueList);

    List<Venue> toModelList(List<VenueEntity> venueEntityList);
}
