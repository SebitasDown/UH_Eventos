package com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Integer capacity;

    @OneToMany(
            mappedBy = "venue",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<EventEntity> events = new ArrayList<>();


    //Helpers
    public void addEvent(EventEntity event){
        events.add(event);
        event.setVenue(this);
    }

    public void removeEvent(EventEntity event){
        events.remove(event);
        event.setVenue(null);
    }
}
