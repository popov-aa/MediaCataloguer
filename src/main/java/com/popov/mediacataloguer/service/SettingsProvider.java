package com.popov.mediacataloguer.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.File;
import java.io.IOException;

public class SettingsProvider implements Provider<Settings> {

    private final Settings settings;

    @Inject
    public SettingsProvider(ObjectMapper objectMapper) {
        File settingsFile = new File(String.format("%s/.config/MediaCataloguer.json", System.getProperty("user.home")));
        Settings _settings;
        try {
            _settings = objectMapper.readValue(settingsFile, Settings.class);
        } catch (IOException e) {
            _settings = new Settings(objectMapper);
        }
        settings = _settings;
    }

    public Settings get() {
        return settings;
    }

}
