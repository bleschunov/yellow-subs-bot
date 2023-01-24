package com.bleschunov.yellowsubs.controller;

import com.bleschunov.yellowsubs.dto.TicketDto;
import com.bleschunov.yellowsubs.service.TicketService;
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
@RequestMapping("/ticket")
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestBody TicketDto ticketDto) {
        log.info(ticketDto.toString());
        ticketService.createOrUpdateTicket(ticketDto);
        return ResponseEntity.ok().build();
    }
}
