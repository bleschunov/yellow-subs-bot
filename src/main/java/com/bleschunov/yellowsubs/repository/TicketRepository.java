package com.bleschunov.yellowsubs.repository;

import com.bleschunov.yellowsubs.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Optional<Ticket> findByTimepadId(String timepadId);
}
