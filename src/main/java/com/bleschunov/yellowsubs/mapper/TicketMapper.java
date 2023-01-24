package com.bleschunov.yellowsubs.mapper;

import com.bleschunov.yellowsubs.dto.TicketDto;
import com.bleschunov.yellowsubs.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "createdAt", qualifiedByName = "toZonedDateTime")
    Ticket toEntity(TicketDto ticketDto);

    @Mapping(target = "createdAt", qualifiedByName = "toZonedDateTime")
    Ticket updateEntity(@MappingTarget Ticket ticket, TicketDto ticketDto);

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
