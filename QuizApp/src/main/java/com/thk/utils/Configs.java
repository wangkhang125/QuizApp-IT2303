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
    
    public static final int FIXED_QUESTION_AMOUNT = 10;
    public static final double[] SPECIFIC_QUESTION_RATES = {0.4, 0.4, 0.2};
}
