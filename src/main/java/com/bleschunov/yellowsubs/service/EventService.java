package com.bleschunov.yellowsubs.service;

import com.bleschunov.yellowsubs.dto.EventDto;
import com.bleschunov.yellowsubs.mapper.EventMapper;
import com.bleschunov.yellowsubs.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public void createOrUpdateEvent(EventDto eventDto) {
        eventRepository.findByTimepadId(eventDto.getTimepadId()).ifPresentOrElse(
                event -> eventMapper.updateEntity(event, eventDto),
                () -> eventRepository.save(eventMapper.toEntity(eventDto))
        );
    }
}
