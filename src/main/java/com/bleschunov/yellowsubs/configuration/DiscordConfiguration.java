package com.bleschunov.yellowsubs.configuration;

import lombok.Setter;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.server.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bleschunov Dmitry
 */
@Configuration
@Setter
public class DiscordConfiguration {
    @Bean
    public DiscordApi discordApi() {
        String token = "MTA2MjcyMzE5MDY2NjgzODA5Ng.GrJIlp.UK_gX6eWMgJf7RNe-V781mAGp1gfgztUI9EVZU";
        return new DiscordApiBuilder().setToken(token).addIntents(Intent.GUILD_MEMBERS, Intent.MESSAGE_CONTENT).login().join();
    }

    @Bean
    public Server discordServer() {
        return discordApi().getServerById(1056312348656279582L).get();
    }
}
