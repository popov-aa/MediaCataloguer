package com.popov.mediacataloguer;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.popov.mediacataloguer.service.Settings;
import com.popov.mediacataloguer.service.SettingsProvider;
import com.popov.mediacataloguer.utils.icons.IconProvider;
import com.popov.mediacataloguer.utils.icons.IconProviderImpl;

public class MediaCataloguerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IconProvider.class).to(IconProviderImpl.class).in(Scopes.SINGLETON);
//        bind(SettingsProvider.class).to(SettingsProviderImpl.class).in(Scopes.SINGLETON);
        bind(Settings.class).toProvider(SettingsProvider.class).in(Scopes.SINGLETON);
    }
}
