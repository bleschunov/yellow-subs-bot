package com.bleschunov.yellowsubs.service;

import com.bleschunov.yellowsubs.dto.AppUserDto;
import com.bleschunov.yellowsubs.model.AppUser;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Service
public class DiscordService {

    private AppUserService appUserService;
    private final Server discordServer;

    public DiscordService(AppUserService appUserService, Server discordServer) {
        this.appUserService = appUserService;
        this.discordServer = discordServer;
        appUserService.setDiscordService(this);

        discordServer.addServerMemberJoinListener(this::updateUserOnServer);
    }

    public void updateUserOnServer(ServerMemberJoinEvent event) {
        User user = event.getUser();
        updateUserOnServer(user, discordServer);
    }

    public void updateUserOnServer(AppUser appUser) {
        discordServer.getMemberByDiscriminatedName(appUser.getDiscordDiscriminatedName())
                .ifPresent(user -> updateUserOnServer(user, discordServer));
    }

    private void updateUserOnServer(User user, Server server) {
        AppUserDto appUserDto = AppUserDto.builder()
                .discordId(user.getId())
                .discordDiscriminatedName(user.getDiscriminatedName())
                .build();

        List<String> rolesAsList = Arrays.asList(appUserService.getOrCreateAppUser(appUserDto).getDiscordRoles().split(", "));

        server.getRoles().stream().filter(role -> rolesAsList.contains(role.getName())).forEach(user::addRole);
    }
}
