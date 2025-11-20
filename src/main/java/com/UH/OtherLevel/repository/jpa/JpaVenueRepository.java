package com.UH.OtherLevel.repository.jpa;

import com.UH.OtherLevel.entities.VenueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaVenueRepository extends JpaRepository<VenueEntity, Long> {
    boolean existsByName (String name);
    boolean existsById (Long id);
    Page<VenueEntity> findAll(Pageable pageable);
}