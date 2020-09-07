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
    void reject();
}
