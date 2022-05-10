package com.example.seapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChapterTest {

    @Test
    public void getCHAPTER_NAME() {
        Chapter c = new Chapter("CSC447", 0.9f, 0.7f);
        assertEquals("CSC447", c.getCHAPTER_NAME());
    }

    @Test
    public void getNB_OF_STUDIED_HOURS() {
        Chapter c = new Chapter("CSC447", 0.9f, null);
        assertEquals(null, c.getNB_OF_STUDIED_HOURS());
    }
}