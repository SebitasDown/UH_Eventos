package com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter;

import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper.VenueEntityMapper;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaVenueRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class VenueRepositoryAdapter implements VenueRepositoryPort {
    private final JpaVenueRepository venueRepository;
    private final VenueEntityMapper venueEntityMapper;

    @Override
    public Venue save(Venue venue) {
        log.info("DB_SAVE_VENUE name={}", venue.getName());
        VenueEntity entity = venueEntityMapper.toEntity(venue);
        VenueEntity saved = venueRepository.save(entity);
        log.info("DB_SAVE_VENUE_SUCCESS id={}", saved.getId());
        return venueEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        log.info("DB_FIND_VENUE_BY_ID id={}", id);
        return venueRepository.findById(id)
                .map(venueEntityMapper::toDomain);
    }

    @Override
    public List<Venue> findAll() {
        log.info("DB_FIND_ALL_VENUES");
        List<VenueEntity> entities = venueRepository.findAll();
        log.info("DB_FIND_ALL_VENUES_SUCCESS count={}", entities.size());
        return venueEntityMapper.toModelList(entities);
    }

    @Override
    public boolean deleteById(Long id) {
        log.info("DB_DELETE_VENUE_BY_ID id={}", id);
        if (!venueRepository.existsById(id)) {
            log.warn("DB_DELETE_VENUE_NOT_FOUND id={}", id);
            return false;
        }
        venueRepository.deleteById(id);
        log.info("DB_DELETE_VENUE_SUCCESS id={}", id);
        return true;
    }
}
