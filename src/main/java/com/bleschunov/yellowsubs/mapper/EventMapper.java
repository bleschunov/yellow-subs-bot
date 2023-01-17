package com.bleschunov.yellowsubs.mapper;

import com.bleschunov.yellowsubs.dto.EventDto;
import com.bleschunov.yellowsubs.model.AppDate;
import com.bleschunov.yellowsubs.model.Event;
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
public interface EventMapper {

    @Mapping(target = "startsAt", qualifiedByName = "toZonedDateTime")
    @Mapping(target = "endsAt", qualifiedByName = "toZonedDateTime")
    @Mapping(target = "createdAt", qualifiedByName = "toZonedDateTime")
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Event toEntity(EventDto eventDto);

    @Mapping(target = "startsAt", qualifiedByName = "toZonedDateTime")
    @Mapping(target = "endsAt", qualifiedByName = "toZonedDateTime")
    @Mapping(target = "createdAt", qualifiedByName = "toZonedDateTime")
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Event updateEntity(@MappingTarget Event event, EventDto eventDto);

    @Named("toZonedDateTime")
    default ZonedDateTime convertAppDateToZonedDateTime(AppDate appDate) {
        LocalDateTime localDateTime = LocalDateTime.parse(
                appDate.getDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
        );
        ZoneId zoneId = ZoneId.of(appDate.getTimezone());
        return ZonedDateTime.of(localDateTime, zoneId);
    }
}
