package com.jean.blue.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevProfile implements MyProfile {
    @Override
    public String getEnvProfile() {
        return "Dev Env";
    }
}
