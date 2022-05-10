package com.example.seapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseTest {

    @Test
    public void getID() {
        Course c = new Course(1, "Software Engineering", "SDLC", "A", "Khaleel Mershed");
        assertEquals(1, c.getID());
    }

    @Test
    public void getC_SECTION() {
        Course c = new Course(1, "Software Engineering", "SDLC", "A", "Khaleel Mershed");
        c.setC_SECTION("B");
        assertEquals("B", c.getC_SECTION());
    }

    @Test
    public void setI_NAME() {
        Course c = new Course(1, "Software Engineering", "SDLC", "A", "Khaleel Mershed");
        assertEquals("Khaleel Mershed", c.getI_NAME());
    }
}