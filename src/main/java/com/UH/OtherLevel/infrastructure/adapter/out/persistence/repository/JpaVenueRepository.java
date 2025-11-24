package com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository;

import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVenueRepository extends JpaRepository<VenueEntity, Long> {
}
