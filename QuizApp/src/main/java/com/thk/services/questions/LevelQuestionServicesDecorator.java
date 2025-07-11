/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.questions;

import com.thk.pojo.Level;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelQuestionServicesDecorator extends QuestionDecorator {
    private Level lvl;

    public LevelQuestionServicesDecorator(BaseQuestionServices decorator, Level lvl) {
        super(decorator);
        this.lvl = lvl;
    }

    public LevelQuestionServicesDecorator(BaseQuestionServices decorator, int lvlID) {
        super(decorator);
        this.lvl = new Level(lvlID);
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND level_id = ?";
        params.add(this.lvl.getId());
        return sql;
    }
}
