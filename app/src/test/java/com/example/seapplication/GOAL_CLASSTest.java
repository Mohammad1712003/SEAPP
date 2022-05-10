package com.example.seapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class GOAL_CLASSTest {

    @Test
    public void getCourse() {
        GOAL_CLASS g = new GOAL_CLASS("Final's chapters", "Software engineering", 10);
        assertEquals("Software engineering", g.getCourse());
    }

    @Test
    public void testToString() {
        GOAL_CLASS g = new GOAL_CLASS("Final's chapters", "Software engineering", 10);
        assertEquals("GOAL is to study  "+g.getGOAL()+" of "+g.getCourse()+" in "+g.duration+"Hour/s", g.toString());

    }
}