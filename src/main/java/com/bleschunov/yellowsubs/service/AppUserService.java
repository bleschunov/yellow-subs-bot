package com.bleschunov.yellowsubs.service;

import com.bleschunov.yellowsubs.dto.AppUserDto;
import com.bleschunov.yellowsubs.mapper.AppUserMapper;
import com.bleschunov.yellowsubs.model.AppUser;
import com.bleschunov.yellowsubs.repository.AppUserRepository;
import com.bleschunov.yellowsubs.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Service
@Setter
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final TicketRepository ticketRepository;
    private DiscordService discordService;
    private final AppUserMapper appUserMapper;

    public AppUser getOrCreateAppUser(AppUserDto appUserDto) {
        return appUserRepository.findByDiscordDiscriminatedName(appUserDto.getDiscordDiscriminatedName())
                .orElseGet(() -> appUserRepository.save(appUserMapper.toEntity(appUserDto)));
    }

    @Transactional
    public void updateUser(String discordDiscriminatedName) {
        AppUser appUser = appUserRepository.findByDiscordDiscriminatedName(discordDiscriminatedName).get();
        assignRoles(appUser);
        discordService.updateUserOnServer(appUser);
    }

    @Transactional
    public void assignRoles(AppUser appUser) {
        List<String> discordRoles = new ArrayList<>();

        appUser.getTickets().stream()
                .filter(ticket -> ticket.getStatus().equals("ok"))
                .forEach(ticket -> discordRoles.add(ticket.getEvent().getTitle()));

        appUser.setDiscordRoles(String.join(", ", discordRoles));
    }
}
