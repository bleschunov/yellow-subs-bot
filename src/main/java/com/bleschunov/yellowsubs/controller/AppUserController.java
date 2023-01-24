package com.bleschunov.yellowsubs.controller;

import com.bleschunov.yellowsubs.dto.AppUserDto;
import com.bleschunov.yellowsubs.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequestMapping("/app_user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping
    public void createOrUpdateAppUser(@RequestBody AppUserDto appUserDto) {
        appUserService.getOrCreateAppUser(appUserDto);

    }
}
