package com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper;

import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.VenueEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T16:03:31-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class VenueEntityMapperImpl implements VenueEntityMapper {

    @Override
    public VenueEntity toEntity(Venue venue) {
        if ( venue == null ) {
            return null;
        }

        VenueEntity venueEntity = new VenueEntity();

        venueEntity.setId( venue.getId() );
        venueEntity.setName( venue.getName() );
        venueEntity.setAddress( venue.getAddress() );
        venueEntity.setCapacity( venue.getCapacity() );

        return venueEntity;
    }

    @Override
    public Venue toDomain(VenueEntity venueEntity) {
        if ( venueEntity == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setId( venueEntity.getId() );
        venue.setName( venueEntity.getName() );
        venue.setCapacity( venueEntity.getCapacity() );
        venue.setAddress( venueEntity.getAddress() );

        return venue;
    }

    @Override
    public List<VenueEntity> toEntityList(List<Venue> venueList) {
        if ( venueList == null ) {
            return null;
        }

        List<VenueEntity> list = new ArrayList<VenueEntity>( venueList.size() );
        for ( Venue venue : venueList ) {
            list.add( toEntity( venue ) );
        }

        return list;
    }

    @Override
    public List<Venue> toModelList(List<VenueEntity> venueEntityList) {
        if ( venueEntityList == null ) {
            return null;
        }

        List<Venue> list = new ArrayList<Venue>( venueEntityList.size() );
        for ( VenueEntity venueEntity : venueEntityList ) {
            list.add( toDomain( venueEntity ) );
        }

        return list;
    }
}
