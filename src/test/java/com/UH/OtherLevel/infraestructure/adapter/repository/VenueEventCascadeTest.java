package com.UH.OtherLevel.infrastructure.adapter.repository;

import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaEventRepository;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaVenueRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
    void deleteVenue_shouldDeleteEvents(){
        VenueEntity venue = new VenueEntity();
        venue.setName("Main Hall");

        EventEntity event = new EventEntity();
        event.setName("Party");

        venue.addEvent(event);

        venueRepository.save(venue);
        venueRepository.delete(venue);

        assertTrue(eventRepository.findAll().isEmpty());
    }
}
