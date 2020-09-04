package com.popov.mediacataloguer.swing;

import javax.swing.*;
import java.awt.*;

public class Utils {
    static void replaceWindow(JDialog dialog) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
                (int) ((dimension.getWidth() - dialog.getWidth()) / 2),
                (int) ((dimension.getHeight() - dialog.getHeight()) / 2)
        );
    }
}
