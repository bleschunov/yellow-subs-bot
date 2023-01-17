package com.bleschunov.yellowsubs.repository;

import com.bleschunov.yellowsubs.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Optional<Event> findByTimepadId(long timepadId);
}
