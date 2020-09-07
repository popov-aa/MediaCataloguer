package com.popov.mediacataloguer.swing;

import com.google.inject.Injector;
import com.popov.mediacataloguer.service.Settings;
import com.popov.mediacataloguer.swing.dialogs.ExecDialog;
import com.popov.mediacataloguer.utils.icons.IconKind;
import com.popov.mediacataloguer.utils.icons.IconProvider;
import com.popov.mediacataloguer.utils.icons.IconTarget;
import org.w3c.dom.events.Event;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JPanel contentPane;
    private JButton buttonClose;
    private JComboBox comboBox1;
    private JButton buttonMediaProfileCreate;
    private JButton buttonMediaProfileEdit;
    private JButton buttonMediaProfileRemove;

    final Settings settings;
    final private IconProvider iconProvider;
    final private Provider<MediaProfileEditDialog> mediaProfileEditDialogProvider;

    @Inject
    public MainWindow(
            Settings settings,
            IconProvider iconProvider,
            Provider<MediaProfileEditDialog> mediaProfileEditDialogProvider
    ) {
        this.settings = settings;
        this.iconProvider = iconProvider;
        this.mediaProfileEditDialogProvider = mediaProfileEditDialogProvider;

        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonClose);

        buttonClose.addActionListener(event -> dispose());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(event -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonMediaProfileCreate.setIcon(iconProvider.getIcon(IconKind.Create, IconTarget.Button));
        buttonMediaProfileEdit.setIcon(iconProvider.getIcon(IconKind.Edit, IconTarget.Button));
        buttonMediaProfileRemove.setIcon(iconProvider.getIcon(IconKind.Remove, IconTarget.Button));

        buttonMediaProfileCreate.addActionListener(this::buttonMediaProfileCreateOnClicked);
        buttonMediaProfileEdit.addActionListener(this::buttonMediaProfileEditOnClicked);
        buttonMediaProfileRemove.addActionListener(this::buttonMediaProfileRemoveOnClicked);

        pack();
        setIconImage(iconProvider.getIcon(IconKind.FolderOpen, IconTarget.WindowIcon).getImage());
        setTitle("Media cataloguer");
        Utils.replaceWindow(this);
    }

    private void buttonMediaProfileCreateOnClicked(ActionEvent actionEvent) {
        MediaProfileEditDialog mediaProfileEditDialog = mediaProfileEditDialogProvider.get();
        if (mediaProfileEditDialog.exec() == ExecDialog.Result.Accepted) {
            settings.getImportProfiles().add(mediaProfileEditDialog.getImportProfile());
        }
    }

    private void buttonMediaProfileEditOnClicked(ActionEvent actionEvent) {
        MediaProfileEditDialog mediaProfileEditDialog = mediaProfileEditDialogProvider.get();
        mediaProfileEditDialog.setAction(MediaProfileEditDialog.Action.Edit);
        if (mediaProfileEditDialog.exec() == ExecDialog.Result.Accepted) {

        }
    }

    private void buttonMediaProfileRemoveOnClicked(ActionEvent actionEvent) {

    }
}
