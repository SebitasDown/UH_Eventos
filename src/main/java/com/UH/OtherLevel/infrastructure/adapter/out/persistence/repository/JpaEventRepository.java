package com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository;

import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {
}
