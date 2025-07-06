package com.sealcia.utils;

import com.sealcia.services.CategoryServices;
import com.sealcia.services.LevelServices;
import com.sealcia.services.QuestionServices;

public class Configs {
    public static final LevelServices levelServices = new LevelServices();
    public static final QuestionServices questionService = new QuestionServices();
    public static final CategoryServices categoryServices = new CategoryServices();
}
