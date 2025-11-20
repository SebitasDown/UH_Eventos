package com.UH.OtherLevel.repository.jpa;


import com.UH.OtherLevel.entities.EventEntity;
import com.UH.OtherLevel.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {
    Boolean existsByName (String name);
}