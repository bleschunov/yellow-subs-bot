package com.bleschunov.yellowsubs.mapper;

import com.bleschunov.yellowsubs.dto.TicketDto;
import com.bleschunov.yellowsubs.exception.BusinessException;
import com.bleschunov.yellowsubs.model.Answer;
import com.bleschunov.yellowsubs.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "ownerDiscordNickname", source = "answers", qualifiedByName = "toDiscordNickname")
    @Mapping(target = "createdAt", qualifiedByName = "toZonedDateTime")
    Ticket toEntity(TicketDto ticketDto);

    @Mapping(target = "ownerDiscordNickname", source = "answers", qualifiedByName = "toDiscordNickname")
    @Mapping(target = "createdAt", qualifiedByName = "toZonedDateTime")
    Ticket updateEntity(@MappingTarget Ticket ticket, TicketDto ticketDto);

    @Named("toDiscordNickname")
    default String extractDiscordNickname(List<Answer> answers) {
        Answer desiredAnswer = answers.stream().filter(answer -> answer.getName().toLowerCase().contains("discord"))
                .findFirst().orElseThrow(() -> new BusinessException("Discord nickname is not provided for ticket"));
        return desiredAnswer.getValue();
    }

    @Named("toZonedDateTime")
    default ZonedDateTime convertAppDateToZonedDateTime(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(
                date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        return ZonedDateTime.of(localDateTime, zoneId);
    }
}
