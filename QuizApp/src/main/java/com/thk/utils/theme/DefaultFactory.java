package com.thk.utils.theme;

import com.thk.quizapp.App;

public class DefaultFactory implements ThemeFactory {
    @Override
    public String getStyleSheet() {
        return App.class.getResource("styles.css").toExternalForm();
    }
}
