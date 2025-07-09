package com.thk.utils;

import com.thk.services.CategoryServices;
import com.thk.services.LevelServices;
import com.thk.services.QuestionServices;

public class Configs {
    public static final LevelServices levelServices = new LevelServices();
    public static final QuestionServices questionService = new QuestionServices();
    public static final CategoryServices categoryServices = new CategoryServices();
}
