package com.bleschunov.yellowsubs.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@Builder
public class AppUserDto {
    private long discordId;
    private String discordDiscriminatedName;
    private String timepadEmail;
}
