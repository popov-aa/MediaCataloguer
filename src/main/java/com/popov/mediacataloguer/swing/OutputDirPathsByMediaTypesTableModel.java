package com.popov.mediacataloguer.swing;

import com.popov.mediacataloguer.MediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class OutputDirPathsByMediaTypesTableModel extends AbstractTableModel {

    @Getter @Setter
    private Map<MediaType,String> outputDirPaths;

    enum Column {
        Title,
        DirPath,
    };

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
        String outputDirPath = outputDirPaths == null ? null : outputDirPaths.get(mediaType);
        return switch (column) {
            case Title -> mediaType.toString();
            case DirPath -> outputDirPath == null ? "disabled" : outputDirPath;
        };
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return switch (Column.values()[c]) {
            case Title -> String.class;
            case DirPath -> String.class;
        };
    }

}
