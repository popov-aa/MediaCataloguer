package com.popov.mediacataloguer.utils.icons;

import javax.swing.*;

public interface IconProvider {
    ImageIcon getIcon(IconKind iconKind, IconTarget iconTarget);
}