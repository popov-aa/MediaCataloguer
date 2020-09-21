package com.popov.mediacataloguer.swing.dialogs;

import java.awt.*;

public interface ExecDialog {
    enum Result {
        Accepted,
        Rejected
    }

    Result exec();
    Result getExecResult();
    void accept();
    default void accepted() {
        // Nothing to do
    }
    void reject();
    default void rejected() {
        // Nothing to do
    }
}
