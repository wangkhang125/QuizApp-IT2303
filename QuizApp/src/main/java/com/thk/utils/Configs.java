package com.thk.utils;

import com.thk.services.CategoryServices;
import com.thk.services.LevelServices;
import com.thk.services.questions.QuestionServices;
import com.thk.services.questions.UpdateQuestionServices;
import com.thk.services.questions.BaseQuestionServices;

public class Configs {
    public static final LevelServices levelServices = new LevelServices();
    public static final CategoryServices categoryServices = new CategoryServices();
    public static final BaseQuestionServices questionServices = new QuestionServices();
    public static final UpdateQuestionServices updateQuestionServices = new UpdateQuestionServices();
}
