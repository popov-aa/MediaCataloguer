package com.popov.mediacataloguer.swing;

import com.popov.mediacataloguer.ImportProfile;
import com.popov.mediacataloguer.service.Settings;
import com.popov.mediacataloguer.utils.icons.IconKind;
import com.popov.mediacataloguer.utils.icons.IconProvider;
import com.popov.mediacataloguer.utils.icons.IconTarget;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.*;

public class MediaProfileEditDialog extends JDialog {

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

    private Action action;
    private ImportProfile importProfile;

    private IconProvider iconProvider;

    @Inject
    public MediaProfileEditDialog(IconProvider iconProvider, ImportProfile importProfile) {
        this.iconProvider = iconProvider;
        this.action = importProfile == null ? Action.Create : Action.Edit;
        if (importProfile == null) {
            importProfile = new ImportProfile();
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle(this.action == Action.Create ? "Creating media profile" : "Editing import profile");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

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

        tableOutputDirectories.setModel(new OutputDirPathsByMediaTypesTableModel(importProfile.getOutputDirPaths()));

        pack();
        Utils.replaceWindow(this);
        this.setIconImage(iconProvider.getIcon(IconKind.Create, IconTarget.WindowIcon).getImage());
        validateData();
    }

    @Inject
    public MediaProfileEditDialog(IconProvider iconProvider) {
        this(iconProvider, null);
    }

    private void onOK() {
        dispose();
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
        System.out.println(textFieldTitle.getText().trim());
        return textFieldTitle.getText().trim().length() > 0 && (checkBoxRequestInputDirectory.isSelected() || textFieldInputDirectory.getText().length() > 0);
    }
    private void validateData() {
        buttonOK.setEnabled(isDataValid());
    }
}
