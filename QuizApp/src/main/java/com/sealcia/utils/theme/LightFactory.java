package com.sealcia.utils.theme;

import com.sealcia.quizapp.App;

public class LightFactory implements ThemeFactory {
    @Override
    public String getStyleSheet() {
        return App.class.getResource("light.css").toExternalForm();
    }
}
