package com.bleschunov.yellowsubs.mapper;

import com.bleschunov.yellowsubs.dto.AppUserDto;
import com.bleschunov.yellowsubs.model.AppUser;
import org.mapstruct.Mapper;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUser toEntity(AppUserDto appUserDto);
}
