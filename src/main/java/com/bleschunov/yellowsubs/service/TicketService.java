package com.bleschunov.yellowsubs.service;

import com.bleschunov.yellowsubs.dto.AppUserDto;
import com.bleschunov.yellowsubs.dto.TicketDto;
import com.bleschunov.yellowsubs.exception.BusinessException;
import com.bleschunov.yellowsubs.mapper.TicketMapper;
import com.bleschunov.yellowsubs.model.Answer;
import com.bleschunov.yellowsubs.model.AppUser;
import com.bleschunov.yellowsubs.model.Ticket;
import com.bleschunov.yellowsubs.repository.EventRepository;
import com.bleschunov.yellowsubs.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AppUserService appUserService;
    private final EventRepository eventRepository;
    private final TicketMapper ticketMapper;
    private final DiscordService discordService;

    public void createOrUpdateTicket(TicketDto ticketDto) {
        ticketRepository.findByTimepadId(ticketDto.getTimepadId())
                .ifPresent(ticket -> ticketMapper.updateEntity(ticket, ticketDto));

        Ticket ticket = ticketMapper.toEntity(ticketDto);

        eventRepository.findByTimepadId(ticketDto.getEventId())
                .ifPresent(ticket::setEvent);

        AppUserDto appUserDto = AppUserDto.builder()
                .timepadEmail(ticketDto.getOwnerTimepadEmail())
                .discordDiscriminatedName(extractDiscordDiscriminatedName(ticketDto.getAnswers()))
                .build();

        AppUser owner = appUserService.getOrCreateAppUser(appUserDto);
        ticket.setOwner(owner);
        ticketRepository.save(ticket);

        appUserService.updateUser(owner.getDiscordDiscriminatedName());
    }

    private String extractDiscordDiscriminatedName(List<Answer> answers) {
        Answer desiredAnswer = answers.stream().filter(answer -> answer.getName().toLowerCase().contains("discord"))
                .findFirst().orElseThrow(() -> new BusinessException("Discord nickname is not provided for ticket"));
        return desiredAnswer.getValue();
    }
}
