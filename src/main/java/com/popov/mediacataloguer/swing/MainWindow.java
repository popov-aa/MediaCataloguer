package com.popov.mediacataloguer.swing;

import com.google.inject.Injector;
import com.popov.mediacataloguer.utils.icons.IconKind;
import com.popov.mediacataloguer.utils.icons.IconProvider;
import com.popov.mediacataloguer.utils.icons.IconTarget;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JButton buttonMediaProfileCreate;
    private JButton buttonMediaProfileEdit;
    private JButton buttonMediaProfileRemove;

    protected IconProvider iconProvider;
    protected Injector injector;

    @Inject
    public MainWindow(IconProvider iconProvider, Injector injector) {
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

        buttonMediaProfileCreate.setIcon(iconProvider.getIcon(IconKind.Create, IconTarget.Button));
        buttonMediaProfileEdit.setIcon(iconProvider.getIcon(IconKind.Edit, IconTarget.Button));
        buttonMediaProfileRemove.setIcon(iconProvider.getIcon(IconKind.Remove, IconTarget.Button));

        buttonMediaProfileCreate.addActionListener(this::buttonMediaProfileCreateOnClicked);
        buttonMediaProfileEdit.addActionListener(this::buttonMediaProfileEditOnClicked);
        buttonMediaProfileRemove.addActionListener(this::buttonMediaProfileRemoveOnClicked);

        pack();
        Utils.replaceWindow(this);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void buttonMediaProfileCreateOnClicked(ActionEvent actionEvent) {
        MediaProfileEditDialog mediaProfileEditDialog = new MediaProfileEditDialog(iconProvider);
        mediaProfileEditDialog.setVisible(true);
    }

    private void buttonMediaProfileEditOnClicked(ActionEvent actionEvent) {
        MediaProfileEditDialog mediaProfileEditDialog = new MediaProfileEditDialog(iconProvider);
        mediaProfileEditDialog.setVisible(true);
    }

    private void buttonMediaProfileRemoveOnClicked(ActionEvent actionEvent) {

    }
}
