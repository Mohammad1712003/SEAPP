package com.example.seapplication;

public class Course {
          int ID;
          String C_NAME;
          String C_TITLE;
          String C_SECTION;
          String I_NAME;

    public Course(int iD,String c_NAME, String c_TITLE, String c_SECTION, String i_NAME) {
        C_NAME = c_NAME;
        C_TITLE = c_TITLE;
        C_SECTION = c_SECTION;
        I_NAME = i_NAME;
        ID=iD;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getC_NAME() {
        return C_NAME;
    }

    public void setC_NAME(String c_NAME) {
        C_NAME = c_NAME;
    }

    public String getC_TITLE() {
        return C_TITLE;
    }

    public void setC_TITLE(String c_TITLE) {
        C_TITLE = c_TITLE;
    }

    public String getC_SECTION() {
        return C_SECTION;
    }

    public void setC_SECTION(String c_SECTION) {
        C_SECTION = c_SECTION;
    }

    public String getI_NAME() {
        return I_NAME;
    }

    public void setI_NAME(String i_NAME) {
        I_NAME = i_NAME;
    }
}
