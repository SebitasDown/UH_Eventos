package com.UH.OtherLevel.domain.exceptions.eventExceptions;

public class InvalidEventDateException extends RuntimeException{

    public InvalidEventDateException(){
        super("Event date cannot be in the past");
    }
}
