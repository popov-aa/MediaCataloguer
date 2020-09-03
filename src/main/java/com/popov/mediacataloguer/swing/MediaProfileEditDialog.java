package com.popov.mediacataloguer.swing;

import com.popov.mediacataloguer.utils.icons.IconKind;
import com.popov.mediacataloguer.utils.icons.IconProvider;
import com.popov.mediacataloguer.utils.icons.IconTarget;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.*;

public class MediaProfileEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton openButton;
    private JCheckBox requestInputDirectoryCheckBox;
    private JTable table1;

    private IconProvider iconProvider;

    @Inject
    public MediaProfileEditDialog(IconProvider iconProvider) {
        this.iconProvider = iconProvider;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        openButton.setIcon(iconProvider.getIcon(IconKind.FolderOpen, IconTarget.Button));
        openButton.addActionListener(this::openButtonOnClick);

        pack();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void openButtonOnClick(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(this);
    }
}
