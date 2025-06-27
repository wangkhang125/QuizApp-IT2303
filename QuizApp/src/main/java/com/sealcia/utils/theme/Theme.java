package com.sealcia.utils.theme;

import javafx.scene.Scene;

public enum Theme {
    DEFAULT {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new DefaultFactory());
            ThemeManager.applyTheme(scene);
        }
    },
    DARK {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new DarkFactory());
            ThemeManager.applyTheme(scene);
        }
    },
    LIGHT {
        @Override
        public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new LightFactory());
            ThemeManager.applyTheme(scene);
        }
    };
    
    public abstract void updateTheme(Scene scene);
}
