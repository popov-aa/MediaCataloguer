package com.popov.mediacataloguer.service;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

public class SettingsProvider implements Provider<Settings> {

    public interface ChangeListener {
        void changed();
    }

    private final Settings settings;
    private final List<ChangeListener> listeners = new ArrayList<>();

    public SettingsProvider() {
        settings = new Settings();
    }

    @Override
    public Settings get() {
        return settings;
    }

    public void fireSettingsChanged() {
        listeners.forEach(consumer -> consumer.changed());
    }

}
