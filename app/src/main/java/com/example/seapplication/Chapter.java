package com.example.seapplication;

public class Chapter {


       String CHAPTER_NAME;
       Float REQUIRED_NB_OF_HOURS;
       Float NB_OF_STUDIED_HOURS;

    public Chapter(String CHAPTER_NAME, Float REQUIRED_NB_OF_HOURS, Float NB_OF_STUDIED_HOURS) {
        this.CHAPTER_NAME = CHAPTER_NAME;
        this.REQUIRED_NB_OF_HOURS = REQUIRED_NB_OF_HOURS;
        this.NB_OF_STUDIED_HOURS = NB_OF_STUDIED_HOURS;
    }

    public String getCHAPTER_NAME() {
        return CHAPTER_NAME;
    }

    public void setCHAPTER_NAME(String CHAPTER_NAME) {
        this.CHAPTER_NAME = CHAPTER_NAME;
    }

    public Float getREQUIRED_NB_OF_HOURS() {
        return REQUIRED_NB_OF_HOURS;
    }

    public void setREQUIRED_NB_OF_HOURS(Float REQUIRED_NB_OF_HOURS) {
        this.REQUIRED_NB_OF_HOURS = REQUIRED_NB_OF_HOURS;
    }

    public Float getNB_OF_STUDIED_HOURS() {
        return NB_OF_STUDIED_HOURS;
    }

    public void setNB_OF_STUDIED_HOURS(Float NB_OF_STUDIED_HOURS) {
        this.NB_OF_STUDIED_HOURS = NB_OF_STUDIED_HOURS;
    }
}
