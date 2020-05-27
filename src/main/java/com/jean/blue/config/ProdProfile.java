package com.jean.blue.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdProfile implements MyProfile{
    @Override
    public String getEnvProfile() {
        return "Dev Prod";
    }
}
