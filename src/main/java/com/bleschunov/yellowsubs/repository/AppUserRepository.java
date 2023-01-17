package com.bleschunov.yellowsubs.repository;

import com.bleschunov.yellowsubs.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByDiscordNickname(String discordNickname);
}
