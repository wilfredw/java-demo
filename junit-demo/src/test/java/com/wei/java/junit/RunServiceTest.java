package com.wei.java.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RunServiceTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("RunServiceTest start");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("RunServiceTest end");
    }

    @Test
    public void calc() {
        assertEquals("3", new RunService().calc(1, 2).toString());
    }
}