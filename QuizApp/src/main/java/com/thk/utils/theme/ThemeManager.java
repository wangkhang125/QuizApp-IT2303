package com.thk.utils.theme;

import javafx.scene.Scene;

public class ThemeManager {
    private static ThemeFactory themeFactory = new DefaultFactory();

    public static void setThemeFactory(ThemeFactory aThemeFactory) {
        themeFactory = aThemeFactory;
    }

    public static void applyTheme(Scene scene) {
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().add(themeFactory.getStyleSheet());
    }
}
