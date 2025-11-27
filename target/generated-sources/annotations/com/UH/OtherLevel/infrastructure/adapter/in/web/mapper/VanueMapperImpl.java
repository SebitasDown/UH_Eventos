package com.UH.OtherLevel.infrastructure.adapter.in.web.mapper;

import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.CreateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.request.venue.UpdateVenueRequest;
import com.UH.OtherLevel.infrastructure.adapter.in.web.dto.response.venue.VenueResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T17:31:24-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class VanueMapperImpl implements VanueMapper {

    @Override
    public VenueResponse toResponse(Venue venue) {
        if ( venue == null ) {
            return null;
        }

        VenueResponse venueResponse = new VenueResponse();

        venueResponse.setId( venue.getId() );
        venueResponse.setName( venue.getName() );
        venueResponse.setAddress( venue.getAddress() );
        venueResponse.setCapacity( venue.getCapacity() );

        return venueResponse;
    }

    @Override
    public List<VenueResponse> toResponseList(List<Venue> venues) {
        if ( venues == null ) {
            return null;
        }

        List<VenueResponse> list = new ArrayList<VenueResponse>( venues.size() );
        for ( Venue venue : venues ) {
            list.add( toResponse( venue ) );
        }

        return list;
    }

    @Override
    public Venue toModel(CreateVenueRequest request) {
        if ( request == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setName( request.getName() );
        venue.setCapacity( request.getCapacity() );
        venue.setAddress( request.getAddress() );

        return venue;
    }

    @Override
    public Venue toUpdateModel(UpdateVenueRequest request) {
        if ( request == null ) {
            return null;
        }

        Venue venue = new Venue();

        venue.setId( request.getId() );
        venue.setName( request.getName() );
        venue.setCapacity( request.getCapacity() );
        venue.setAddress( request.getAddress() );

        return venue;
    }
}
