package com.UH.OtherLevel.infraestructure.adapter.repository;

import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaEventRepository;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaVenueRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class VenueEventCascadeTest {

    @Autowired
    private JpaVenueRepository venueRepository;

    @Autowired
    private JpaEventRepository eventRepository;

    @Test
    void deleteVenue_shouldDeleteEvents() {

        VenueEntity venue = new VenueEntity();
        venue.setName("Main Hall");

        EventEntity event = new EventEntity();
        event.setName("Party");

        venue.addEvent(event);

        venueRepository.save(venue);

        venueRepository.delete(venue);

        assertTrue(eventRepository.findAll().isEmpty());
    }

    @Test
    void movingEventBetweenVenues_shouldNotDeleteEvent() {
        VenueEntity v1 = new VenueEntity();
        v1.setName("Hall A");

        VenueEntity v2 = new VenueEntity();
        v2.setName("Hall B");

        EventEntity event = new EventEntity();
        event.setName("Conference");


        v1.addEvent(event);

        venueRepository.save(v1);
        venueRepository.save(v2);

        v1.removeEvent(event);
        v2.addEvent(event);

        venueRepository.save(v1);
        venueRepository.save(v2);

        assertFalse(eventRepository.findAll().isEmpty());

    }

    @Test
    void removingEvent_shouldDeleteOrphanEvent(){
        VenueEntity venue = new VenueEntity();
        venue.setName("Auditorium");

        EventEntity event = new EventEntity();
        event.setName("Workshop");

        venue.addEvent(event);

        venueRepository.save(venue);

        venue.removeEvent(event);

        venueRepository.save(venue);

        assertTrue(eventRepository.findAll().isEmpty());
    }
}