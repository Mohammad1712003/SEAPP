package com.example.seapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class ACCOUNTSTest {

    @Test
    public void getUSERNAME() {
        ACCOUNTS a = new ACCOUNTS("Mario", "1234");
        assertEquals("Mario", a.getUSERNAME());
    }

    @Test
    public void setUSERNAME() {
        ACCOUNTS a = new ACCOUNTS("Mario", "1234");
        a.setUSERNAME("hasan");
        assertNotNull(a.getUSERNAME());
    }

    @Test
    public void getPASSWORD() {
        ACCOUNTS a = new ACCOUNTS("Mario", "1234");
        assertEquals("Mario", a.getUSERNAME());
    }
}