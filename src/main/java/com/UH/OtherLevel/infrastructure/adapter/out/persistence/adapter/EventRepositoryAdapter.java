package com.UH.OtherLevel.infrastructure.adapter.out.persistence.adapter;



import com.UH.OtherLevel.application.port.out.EventRepositoryPort;
import com.UH.OtherLevel.domain.model.Event;
import com.UH.OtherLevel.domain.model.SearchEvent;
import com.UH.OtherLevel.domain.pageable.PageRequest;
import com.UH.OtherLevel.domain.pageable.PageResult;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.especification.EventSpecification;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.mapper.EventEntityMapper;
import com.UH.OtherLevel.infrastructure.adapter.out.persistence.repository.JpaEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final JpaEventRepository eventRepository;
    private final EventEntityMapper eventEntityMapper;


    @Override
    public Event save(Event event) {
        EventEntity entity = eventEntityMapper.toEntity(event);
        EventEntity saved = eventRepository.save(entity);
        return eventEntityMapper.toModel(saved);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id)
                .map(eventEntityMapper::toModel);
    }

    @Override
    public List<Event> findAll() {
        return eventEntityMapper.toModelList(eventRepository.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        if (!eventRepository.existsById(id)) return false;
        eventRepository.deleteById(id);
        return true;
    }

    @Override
    public PageResult<Event> findAllPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public List<Event> searchEvents(SearchEvent searchEvent) {
        Specification<EventEntity> spec = (root, query, cb) -> cb.conjunction();

        if (searchEvent.getVenueId() != null) {
            spec = spec.and(EventSpecification.byVenueId(searchEvent.getVenueId()));
        }
        if (searchEvent.getDateFrom() != null) {
            spec = spec.and(EventSpecification.dateTo(searchEvent.getDateFrom()));
        }
        if (searchEvent.getNameContains() != null && !searchEvent.getNameContains().isBlank()) {
            spec = spec.and(EventSpecification.nameContains(searchEvent.getNameContains()));
        }

        List<EventEntity> entities = eventRepository.findAll(spec);
        return eventEntityMapper.toModelList(entities);
    }
}
