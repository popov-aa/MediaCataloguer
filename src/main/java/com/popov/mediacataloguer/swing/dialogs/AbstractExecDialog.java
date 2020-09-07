package com.popov.mediacataloguer.swing.dialogs;

import javax.swing.*;

public class AbstractExecDialog extends JDialog implements ExecDialog {

    private Result result = Result.Rejected;

    @Override
    public Result exec() {
        setVisible(true);
        return result;
    }

    @Override
    public Result getExecResult() {
        return result;
    }

    @Override
    public void accept() {
        result = Result.Accepted;
        dispose();
    }

    @Override
    public void reject() {
        result = Result.Rejected;
        dispose();
    }
}
