package com.popov.mediacataloguer;

import com.google.inject.AbstractModule;
import com.popov.mediacataloguer.service.Settings;
import com.popov.mediacataloguer.swing.MainWindow;
import com.popov.mediacataloguer.utils.icons.IconProvider;
import com.popov.mediacataloguer.utils.icons.IconProviderImpl;

public class MediaCataloguerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Settings.class);
        bind(MainWindow.class);
        bind(IconProvider.class).to(IconProviderImpl.class);
    }
}
