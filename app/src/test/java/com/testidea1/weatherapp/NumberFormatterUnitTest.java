package com.testidea1.weatherapp;

import com.testidea1.weatherapp.utils.NumberFormatter;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberFormatterUnitTest {
    @Test
    public void format_Temperature_isCorrect() {
        double parameter = 18.44;
        String actual = NumberFormatter.formatTemperature(parameter);
        String expected = "18 ºC";
        assertEquals("Formateo fallido de temperatura",expected, actual);
    }

    @Test
    public void format_Percentual_NoDecimal_isCorrect() {
        double parameter = 0.69;
        String actual = NumberFormatter.formatPercent(parameter, 0);
        String expected = "69%";
        assertEquals("Formateo fallido de porcentaje",expected, actual);
    }

    @Test
    public void format_Percentual_OneDecimal_isCorrect() {
        double parameter = 0.692;
        String actual = NumberFormatter.formatPercent(parameter, 1);
        String expected = "69,2%";
        assertEquals("Formateo fallido de porcentaje",expected, actual);
    }
}