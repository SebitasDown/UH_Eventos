package com.UH.OtherLevel.domain.exceptions.eventExceptions;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(Long id){
        super("Event with ID "+ id + " not Found");
    }
    public EventNotFoundException(String message){
        super(message);
    }

}
