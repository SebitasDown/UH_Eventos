package com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter;

import com.UH.OtherLevel.application.port.out.VenueRepositoryPort;
import com.UH.OtherLevel.domain.model.Venue;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper.VenueEntityMapper;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaVenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class VenueRepositoryAdapter implements VenueRepositoryPort {
  private final JpaVenueRepository venueRepository;
  private final VenueEntityMapper venueEntityMapper;

    @Override
    public Venue save(Venue venue) {
        VenueEntity entity = venueEntityMapper.toEntity(venue);
        VenueEntity saved = venueRepository.save(entity);
        return venueEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return venueRepository.findById(id)
                .map(venueEntityMapper::toDomain);
    }

    @Override
    public List<Venue> findAll() {
        return venueEntityMapper.toModelList(venueRepository.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        if (!venueRepository.existsById(id)) return false;
        venueRepository.deleteById(id);
        return true;
    }
}
