package com.bleschunov.yellowsubs.controller;

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
public class EventController {

    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody String event) {
        log.info(event);
        System.out.println(event);
        return ResponseEntity.ok().build();
    }
}
