package com.UH.OtherLevel.domain.exceptions.venueExceptions;

public class VanueCapacityExceededException extends RuntimeException{

    public VanueCapacityExceededException(Integer capacity){
        super("Vanue capacity exceeded. Max allowed: " + capacity);
    }
}
