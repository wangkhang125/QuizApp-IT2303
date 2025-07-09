package com.thk.utils.theme;

import com.thk.quizapp.App;

public class DarkFactory implements ThemeFactory {
    @Override
    public String getStyleSheet() {
        return App.class.getResource("dark.css").toExternalForm();
    }
}
