package com.bleschunov.yellowsubs.controller;

import com.bleschunov.yellowsubs.dto.EventDto;
import com.bleschunov.yellowsubs.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequestMapping("/event")
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Void> createOrUpdateEvent(@RequestBody EventDto eventDto) {
        eventService.createOrUpdateEvent(eventDto);
        return ResponseEntity.ok().build();
    }
}
