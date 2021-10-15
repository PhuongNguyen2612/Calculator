package com.calculator1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    void testCalculate_true() throws Exception {
        assertEquals(2, calculator.calcalate("1+1"));
        assertEquals(18, calculator.calcalate("10-2+3*4-2"));
        assertEquals(11, calculator.calcalate("10-(1-2)*3/3"));
    }

    @Test
    void testCalculate_false() {

    }

}