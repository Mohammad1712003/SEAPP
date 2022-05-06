package com.example.seapplication;

public class GOAL_CLASS {
    String GOAL;
    int duration;
    String Course;

    public GOAL_CLASS(String GOAL, String Course,int duration) {
        this.GOAL = GOAL;
        this.Course=Course;
        this.duration = duration;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public void setGOAL(String GOAL) {
        this.GOAL = GOAL;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGOAL() {
        return GOAL;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return
                "GOAL is to study  " + GOAL +
               " of " +Course+" in "+ duration+ "Hour/s";
    }
}
