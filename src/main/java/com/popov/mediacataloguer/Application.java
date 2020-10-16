package com.popov.mediacataloguer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.popov.mediacataloguer.swing.dialogs.MainWindow;

public class Application {
    public final static void main(String[] args) {
        com.formdev.flatlaf.FlatLightLaf.install();
        Injector injector = Guice.createInjector(new MediaCataloguerModule());
        injector.getInstance(MainWindow.class).setVisible(true);
    }
}
