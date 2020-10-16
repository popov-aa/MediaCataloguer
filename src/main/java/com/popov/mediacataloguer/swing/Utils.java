package com.popov.mediacataloguer.swing;

import javax.swing.*;
import java.awt.*;

public class Utils {
    static public void replaceWindow(Window window) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(
                (int) ((dimension.getWidth() - window.getWidth()) / 2),
                (int) ((dimension.getHeight() - window.getHeight()) / 2)
        );
    }
}
