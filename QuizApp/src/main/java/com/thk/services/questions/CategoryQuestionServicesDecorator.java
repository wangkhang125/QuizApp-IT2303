/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.questions;

import com.thk.pojo.Category;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryQuestionServicesDecorator extends QuestionDecorator {
    private Category cate;

    public CategoryQuestionServicesDecorator(BaseQuestionServices decorator, Category cate) {
        super(decorator);
        this.cate = cate;
    }

    public CategoryQuestionServicesDecorator(BaseQuestionServices decorator, int cateID) {
        super(decorator);
        this.cate = new Category(cateID);
    }
    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND category_id = ?";
        params.add(this.cate.getId());
        return sql;
    }
}
