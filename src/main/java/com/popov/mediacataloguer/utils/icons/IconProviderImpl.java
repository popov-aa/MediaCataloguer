package com.popov.mediacataloguer.utils.icons;

import com.github.fcannizzaro.material.icons.IconMaterial;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class IconProviderImpl implements IconProvider {

    private Map<IconTarget,Integer> iconTargetSizes = new HashMap<>();
    private Map<IconKind,String> iconsNames = new HashMap<>();

    public IconProviderImpl() {
        for (IconTarget iconTarget: IconTarget.values()) {
            iconTargetSizes.put(iconTarget, getIconSize(iconTarget));
        }
        for (IconKind iconKind : IconKind.values()) {
            iconsNames.put(iconKind, getIconName(iconKind));
        }
    }

    @Override
    public ImageIcon getIcon(IconKind iconKind, IconTarget iconTarget) {
        return new IconMaterial(iconsNames.get(iconKind)).size(iconTargetSizes.get(iconTarget)).imageIcon();
    }

    private String getIconName(IconKind iconKind) {
        return switch (iconKind) {
            case Create -> "create_new_folder";
            case Edit -> "folder_open";
            case Remove -> "remove_circle";
            case FolderOpen -> "folder_open";
        };
    }

    private int getIconSize(IconTarget iconTarget) {
        return switch (iconTarget) {
            case Button -> 16;
        };
    }
}
