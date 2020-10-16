package com.popov.mediacataloguer.swing.model;

import com.popov.mediacataloguer.ImportProfile;
import com.popov.mediacataloguer.service.Settings;

import javax.inject.Inject;
import javax.swing.table.AbstractTableModel;

public class ImportProfileTableModel extends AbstractTableModel {

    private final Settings settings;

    enum Column {
        Title
    }

    @Inject
    public ImportProfileTableModel(Settings settings) {
        this.settings = settings;
        this.settings.addListener(() -> {
            fireTableDataChanged();
        });
    }

    @Override
    public int getRowCount() {
        return settings.getImportProfiles().size();
    }

    @Override
    public int getColumnCount() {
        return Column.values().length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Column column = Column.values()[c];
        ImportProfile importProfile = settings.getImportProfiles().get(r);
        return switch (column) {
            case Title -> importProfile.getTitle();
        };
    }
}
