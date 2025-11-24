package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;

import com.UH.OtherLevel.dto.VenueDTO;
import com.UH.OtherLevel.domain.model.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VanueMapper {
    VanueMapper INSTANCE = Mappers.getMapper(VanueMapper.class);

    VenueDTO toDTO(Venue venue);
    Venue toModel(VenueDTO dto);

    List<VenueDTO> toDTOList(List<Venue> venues);
    List<Venue> toModelList(List<VenueDTO> dtos);
}

