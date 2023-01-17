package com.bleschunov.yellowsubs.service;

import com.bleschunov.yellowsubs.dto.TicketDto;
import com.bleschunov.yellowsubs.mapper.TicketMapper;
import com.bleschunov.yellowsubs.model.Ticket;
import com.bleschunov.yellowsubs.repository.AppUserRepository;
import com.bleschunov.yellowsubs.repository.EventRepository;
import com.bleschunov.yellowsubs.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final TicketMapper ticketMapper;

    public void createOrUpdateTicket(TicketDto ticketDto) {
        ticketRepository.findByTimepadId(ticketDto.getTimepadId())
                .ifPresent(event -> ticketMapper.updateEntity(event, ticketDto));

        Ticket ticket = ticketMapper.toEntity(ticketDto);

        appUserRepository.findByDiscordNickname(ticket.getOwnerDiscordNickname())
                .ifPresent(ticket::setAppUser);

        eventRepository.findByTimepadId(ticketDto.getEventId())
                .ifPresent(ticket::setEvent);

        ticketRepository.save(ticket);
    }
}
