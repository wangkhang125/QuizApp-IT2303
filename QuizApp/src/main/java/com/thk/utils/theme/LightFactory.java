package com.thk.utils.theme;

import com.thk.quizapp.App;

public class LightFactory implements ThemeFactory {
    @Override
    public String getStyleSheet() {
        return App.class.getResource("light.css").toExternalForm();
    }
}
