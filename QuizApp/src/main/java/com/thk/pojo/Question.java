package com.thk.pojo;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String content;
    private String hint;
    private String image;
    private Category category;
    private Level level;
    private List<Choice> choices = new ArrayList<>();

    public Question(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.hint = builder.hint;
        this.image = builder.image;
        this.category = builder.category;
        this.level = builder.level;
        this.choices = builder.choices;
    }

    public static class Builder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private Category category;
        private Level level;
        private List<Choice> choices = new ArrayList<>();

        public Builder(String content, Category category, Level level) throws Exception {
            if (content.isEmpty() || category == null || level == null) {
                throw new Exception("DỮ LIỆU KHÔNG PHÙ HỢP!");
            }
            this.content = content;
            this.category = category;
            this.level = level;
        }

        public Builder(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public Builder addHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder addImage(String image) {
            this.image = image;
            return this;
        }

        public Builder addChoice(Choice choice) {
            this.choices.add(choice);
            return this;
        }

        public Builder addAllChoices(List<Choice> choices) {
            this.choices.addAll(choices);
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    
}
