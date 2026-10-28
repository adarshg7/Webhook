package com.adarsh.webhook.service;

import com.adarsh.webhook.dto.CreateEventRequest;
import com.adarsh.webhook.entity.Event;
import com.adarsh.webhook.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Event createEvent(UUID tenantId, String idempotencyKey, CreateEventRequest request){
        try{
            Event event = new Event();
            event.setId(UUID.randomUUID());
            event.setTenantId(tenantId);
            event.setEventType(request.getEventType());
            event.setPayload(request.getPayload());
            event.setIdempotencyKey(idempotencyKey);
            event.setCreatedAt(OffsetDateTime.now());

            return eventRepository.save(event);
        } catch (DataIntegrityViolationException e){
            return eventRepository.findByTenantIdAndIdempotencyKey(tenantId,idempotencyKey)
                    .orElseThrow(() -> e);
        }
    }

    public boolean existsAlready(UUID tenantId, String idempotencyKey) {
        return eventRepository.findByTenantIdAndIdempotencyKey(tenantId, idempotencyKey).isPresent();
    }
}
