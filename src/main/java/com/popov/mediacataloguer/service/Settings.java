package com.popov.mediacataloguer.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.popov.mediacataloguer.ImportProfile;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Settings {

    public interface ChangeListener {
        void changed();
    }

    @JsonIgnore
    private final ObjectMapper objectMapper;

    @JsonIgnore
    private final List<ChangeListener> listeners = new ArrayList<>();

    @Getter @Setter
    private List<ImportProfile> importProfiles = new ArrayList<>();

    @Inject
    public Settings(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void fireSettingsChanged() {
        listeners.forEach(consumer -> consumer.changed());
    }

    public void save() throws IOException {
        File settingsDirectory = new File(String.format("%s/.config/", System.getProperty("user.home")));;
        if (!settingsDirectory.exists()){
            settingsDirectory.mkdirs();
        }
        File settingsFile = new File(String.format("%s/.config/MediaCataloguer.json", System.getProperty("user.home")));
        objectMapper.writeValue(settingsFile, this);
    }

    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ChangeListener listener) {
        listeners.add(listener);
    }

}
