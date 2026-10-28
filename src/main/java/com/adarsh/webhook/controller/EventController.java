package com.adarsh.webhook.controller;

import com.adarsh.webhook.dto.CreateEventRequest;
import com.adarsh.webhook.entity.Event;
import com.adarsh.webhook.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    private static final UUID TEMP_TENANT_ID =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> create(
            @RequestHeader("idempotency-Key") String idempotencyKey,
            @Valid @RequestBody CreateEventRequest request
            ){
        boolean existedBefore = eventService.existsAlready(TEMP_TENANT_ID,idempotencyKey);

        Event event = eventService.createEvent(TEMP_TENANT_ID, idempotencyKey, request);

        HttpStatus status = existedBefore ? HttpStatus.OK : HttpStatus.CREATED;

        return  ResponseEntity.status(status).body(event);
    }

}
