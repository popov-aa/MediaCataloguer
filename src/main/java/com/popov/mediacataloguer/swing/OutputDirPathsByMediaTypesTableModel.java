package com.popov.mediacataloguer.swing;

import com.popov.mediacataloguer.MediaType;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Map;

public class OutputDirPathsByMediaTypesTableModel extends AbstractTableModel {

    private Map<MediaType,String> outputDirPaths;

    enum Column {
        Title,
        DirPath,
        OpenButton
    };

    OutputDirPathsByMediaTypesTableModel(Map<MediaType,String> outputDirPaths) {
        this.outputDirPaths = outputDirPaths;
    }

    @Override
    public int getRowCount() {
        return MediaType.values().length;
    }

    @Override
    public int getColumnCount() {
        return Column.values().length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Column column = Column.values()[c];
        MediaType mediaType = MediaType.values()[r];
        return switch (column) {
            case Title -> mediaType.toString();
            case DirPath -> outputDirPaths.get(mediaType);
            case OpenButton -> new JButton("Open");
        };
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return switch (Column.values()[c]) {
            case Title -> String.class;
            case DirPath -> String.class;
            case OpenButton -> JButton.class;
        };
    }

}
