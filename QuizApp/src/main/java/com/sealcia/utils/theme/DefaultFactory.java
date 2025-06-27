package com.sealcia.utils.theme;

import com.sealcia.quizapp.App;

public class DefaultFactory implements ThemeFactory {
    @Override
    public String getStyleSheet() {
        return App.class.getResource("styles.css").toExternalForm();
    }
}
