package com.popov.mediacataloguer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.popov.mediacataloguer.swing.MainWindow;

import javax.inject.Inject;

public class Application {
    public final static void main(String[] args) {
        Injector injector = Guice.createInjector(new MediaCataloguerModule());
        injector.getInstance(MainWindow.class).setVisible(true);
    }
}
