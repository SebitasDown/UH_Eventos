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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final JpaEventRepository eventRepository;
    private final EventEntityMapper eventEntityMapper;

    @Override
    public Event save(Event event) {
        log.info("DB_SAVE_EVENT name={}", event.getName());
        EventEntity entity = eventEntityMapper.toEntity(event);
        EventEntity saved = eventRepository.save(entity);
        log.info("DB_SAVE_EVENT_SUCCESS id={}", saved.getId());
        return eventEntityMapper.toModel(saved);
    }

    @Override
    public Optional<Event> findById(Long id) {
        log.info("DB_FIND_EVENT_BY_ID id={}", id);
        return eventRepository.findById(id)
                .map(eventEntityMapper::toModel);
    }

    @Override
    public List<Event> findAll() {
        log.info("DB_FIND_ALL_EVENTS");
        List<EventEntity> entities = eventRepository.findAll();
        log.info("DB_FIND_ALL_EVENTS_SUCCESS count={}", entities.size());
        return eventEntityMapper.toModelList(entities);
    }

    @Override
    public boolean deleteById(Long id) {
        log.info("DB_DELETE_EVENT_BY_ID id={}", id);
        if (!eventRepository.existsById(id)) {
            log.warn("DB_DELETE_EVENT_NOT_FOUND id={}", id);
            return false;
        }
        eventRepository.deleteById(id);
        log.info("DB_DELETE_EVENT_SUCCESS id={}", id);
        return true;
    }

    @Override
    public PageResult<Event> findAllPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public List<Event> searchEvents(SearchEvent searchEvent) {
        log.info("DB_SEARCH_EVENTS venueId={} dateFrom={} name={}", searchEvent.getVenueId(), searchEvent.getDateFrom(),
                searchEvent.getNameContains());
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
        log.info("DB_SEARCH_EVENTS_SUCCESS count={}", entities.size());
        return eventEntityMapper.toModelList(entities);
    }
}
