package com.UH.OtherLevel.infrastructure.adapter.out.persistence.especification;

import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecification {

    // Filtro por id de Venue
    public static Specification<EventEntity> byVenueId(Long venueId){
        return (root, query, cb) ->
          venueId == null ? null: cb.equal(root.get("venue").get("id"), venueId);

    }

    // filtro para la fecha de inicio
    public static Specification<EventEntity> dateTo(LocalDateTime start){
        return (root, query, criteriaBuilder) ->
                start == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("date"), start);
    }

    //Filtro para nombre
    public static Specification<EventEntity> nameContains(String text){
        return (root, query, cb) ->
                text == null || text.isBlank() ? null : cb.like(cb.lower(root.get("name")), "%" + text.toLowerCase() + "%");
    }

}