package com.popov.mediacataloguer.swing;

import com.popov.mediacataloguer.ImportProfile;
import com.popov.mediacataloguer.MediaType;
import com.popov.mediacataloguer.swing.dialogs.AbstractExecDialog;
import com.popov.mediacataloguer.utils.icons.IconKind;
import com.popov.mediacataloguer.utils.icons.IconProvider;
import com.popov.mediacataloguer.utils.icons.IconTarget;
import lombok.Getter;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.*;
import java.util.Optional;

public class MediaProfileEditDialog extends AbstractExecDialog {

    enum Action {
        Create,
        Edit
    };

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldTitle;
    private JTextField textFieldInputDirectory;
    private JButton buttonInputDirectoryOpen;
    private JCheckBox checkBoxRequestInputDirectory;
    private JTable tableOutputDirectories;
    private JButton buttonInputDirectoryClear;
    private JButton clearOutputDirectoryButton;
    private JButton openOutputDirectoryButton;

    @Getter
    private ImportProfile importProfile = new ImportProfile();
    @Getter
    private Action action = Action.Create;

    final private OutputDirPathsByMediaTypesTableModel outputDirPathsByMediaTypesTableModel = new OutputDirPathsByMediaTypesTableModel();

    final private IconProvider iconProvider;

    @Inject
    public MediaProfileEditDialog(IconProvider iconProvider) {
        this.iconProvider = iconProvider;

        setImportProfile(importProfile);
        setAction(action);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(event -> onOK());
        buttonCancel.addActionListener(event -> dispose());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contentPane.registerKeyboardAction(event -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        textFieldTitle.getDocument().addDocumentListener(new DocumentListenerAdapter(documentEvent -> validateData()));
        textFieldInputDirectory.getDocument().addDocumentListener(new DocumentListenerAdapter(documentEvent -> validateData()));

        buttonInputDirectoryOpen.setIcon(iconProvider.getIcon(IconKind.FolderOpen, IconTarget.Button));
        buttonInputDirectoryOpen.addActionListener(this::buttonInputDirectoryOpenOnClick);

        buttonInputDirectoryClear.setIcon(iconProvider.getIcon(IconKind.Remove, IconTarget.Button));
        buttonInputDirectoryClear.addActionListener(this::buttonInputDirectoryClearOnClick);

        checkBoxRequestInputDirectory.addActionListener(actionEvent -> validateData());

        tableOutputDirectories.setModel(outputDirPathsByMediaTypesTableModel);
        tableOutputDirectories.getSelectionModel().addListSelectionListener(this::tableOutputDirectoriesSelectionChanged);
        tableOutputDirectories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        openOutputDirectoryButton.setIcon(iconProvider.getIcon(IconKind.FolderOpen, IconTarget.Button));
        openOutputDirectoryButton.addActionListener(this::openOutputDirectoryButtonOnClick);

        clearOutputDirectoryButton.setIcon(iconProvider.getIcon(IconKind.Remove, IconTarget.Button));
        clearOutputDirectoryButton.addActionListener(this::clearOutputDirectoryButtonOnClick);

        pack();
        Utils.replaceWindow(this);
        this.setIconImage(iconProvider.getIcon(IconKind.Create, IconTarget.WindowIcon).getImage());
        validateData();
    }

    public void setImportProfile(ImportProfile importProfile) {
        this.importProfile = importProfile;
        outputDirPathsByMediaTypesTableModel.setOutputDirPaths(this.importProfile.getOutputDirPaths());
    }

    public void setAction(Action action) {
        this.action = action;
        setTitle(this.action == Action.Create ? "Creating media profile" : "Editing import profile");
    }

    private void onOK() {
        accept();
    }

    private void buttonInputDirectoryOpenOnClick(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            textFieldInputDirectory.setText(fileChooser.getSelectedFile().getPath());
        }
    }

    private void buttonInputDirectoryClearOnClick(ActionEvent actionEvent) {
        textFieldInputDirectory.setText("");
    }

    public boolean isDataValid() {
        return textFieldTitle.getText().trim().length() > 0 &&
                (checkBoxRequestInputDirectory.isSelected() || textFieldInputDirectory.getText().length() > 0) &&
                !importProfile.getOutputDirPaths().isEmpty();
    }

    private void validateData() {
        buttonOK.setEnabled(isDataValid());
    }

    private void tableOutputDirectoriesSelectionChanged(ListSelectionEvent e) {
        Optional<MediaType> mediaType = tableOutputDirectories.getSelectedRow() == -1 ?
                Optional.empty() : Optional.of(MediaType.values()[tableOutputDirectories.getSelectedRow()]);
        openOutputDirectoryButton.setEnabled(mediaType.isPresent() ?
                !importProfile.getOutputDirPaths().containsKey(mediaType.get()) : false);
        clearOutputDirectoryButton.setEnabled(mediaType.isPresent() ?
                !openOutputDirectoryButton.isEnabled() : false);
    }

    private void openOutputDirectoryButtonOnClick(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            MediaType mediaType = MediaType.values()[tableOutputDirectories.getSelectedRow()];
            importProfile.getOutputDirPaths().put(mediaType, fileChooser.getSelectedFile().getPath());
            outputDirPathsByMediaTypesTableModel.fireTableDataChanged();
            validateData();
        }
    }

    private void clearOutputDirectoryButtonOnClick(ActionEvent event) {
        MediaType mediaType = MediaType.values()[tableOutputDirectories.getSelectedRow()];
        importProfile.getOutputDirPaths().remove(mediaType);
        outputDirPathsByMediaTypesTableModel.fireTableDataChanged();
        validateData();
    }
}
