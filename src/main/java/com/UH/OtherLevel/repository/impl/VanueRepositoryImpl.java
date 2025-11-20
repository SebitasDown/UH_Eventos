package com.UH.OtherLevel.repository.impl;

import com.UH.OtherLevel.entities.VenueEntity;
import com.UH.OtherLevel.exceptions.BusinessException;
import com.UH.OtherLevel.mapper.EventEntityMapper;
import com.UH.OtherLevel.mapper.VenueEntityMapper;
import com.UH.OtherLevel.model.Venue;
import com.UH.OtherLevel.repository.interfaces.VenueRepository;
import com.UH.OtherLevel.repository.jpa.JpaVenueRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class VanueRepositoryImpl implements VenueRepository {

    private final JpaVenueRepository vanueRepository;


    @Override
    public List<Venue> findAll() {
        List<VenueEntity> venues = vanueRepository.findAll();
        return VenueEntityMapper.INSTANCE.toModelList(venues);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return vanueRepository.findById(id)
                .map(VenueEntityMapper.INSTANCE::toDomain);
    }

    @Override
    public Venue save(Venue venue) {
        if (venue.getName() == null || venue.getName().trim().isEmpty()) {
            throw new BusinessException("BAD_REQUEST", "El nombre del venue no puede estar vacío");
        }

        if (vanueRepository.existsByName(venue.getName())) {
            throw new BusinessException("CONFLICT", "Ya existe un venue con ese nombre");
        }
        VenueEntity entity = VenueEntityMapper.INSTANCE.toEntity(venue);
        VenueEntity saved = vanueRepository.save(entity);

        return VenueEntityMapper.INSTANCE.toDomain(saved);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!vanueRepository.existsById(id)){
            return false;
        }

        vanueRepository.deleteById(id);
        return true;
    }

    @Override
    public Venue update(Venue venue) {
        if (venue.getId() == null){
            throw new BusinessException("BAD_REQUEST","NOT NULL");
        }
        if (vanueRepository.existsByName(venue.getName())) {
            throw new BusinessException("CONFLICT", "Ya existe un venue con ese nombre");
        }

        VenueEntity entity = VenueEntityMapper.INSTANCE.toEntity(venue);

        VenueEntity update = vanueRepository.save(entity);

        return VenueEntityMapper.INSTANCE.toDomain(update);
    }


}
