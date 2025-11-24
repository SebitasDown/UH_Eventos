package com.UH.OtherLevel.domain.exceptions.venueExceptions;

public class VenueNotFoundException extends RuntimeException{

    public VenueNotFoundException(Long id){
        super("Venue with ID "+ id + " not found");
    }
    public VenueNotFoundException(String message){
        super(message);
    }
}
