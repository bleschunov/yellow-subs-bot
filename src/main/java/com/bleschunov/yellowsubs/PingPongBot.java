package com.bleschunov.yellowsubs;

import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;

/**
 * @author Bleschunov Dmitry
 */
@Slf4j
public class PingPongBot {
    public static void main(String[] args) {
        // Insert your bot's token here
        String token = "MTA2MjcyMzE5MDY2NjgzODA5Ng.G_NDFg.foG0kLxgYny_AmmHAQy32N5LpL4qAIY4YCrtYo";

        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
                Server server = event.getServer().get();
                Role role = server.getRolesByName("ticket 1").get(0);
                log.debug(role.getName());
                server.addRoleToUser(event.getMessageAuthor().asUser().get(), role);
            }
        });

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
