package com.sealcia.utils.theme;

import com.sealcia.quizapp.App;

public class DarkFactory implements ThemeFactory {
    @Override
    public String getStyleSheet() {
        return App.class.getResource("dark.css").toExternalForm();
    }
}
