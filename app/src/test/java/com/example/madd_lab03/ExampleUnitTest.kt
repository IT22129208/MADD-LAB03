package com.example.madd_lab03

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DecimalFormat
import java.util.Locale

class CalculatorUnitTest {

    private fun formatAnswer(value: Double): String {
        return if (value % 1.0 == 0.0) {
            String.format(Locale.US, "%.1f", value)
        } else {
            val df = DecimalFormat("#.####")
            df.format(value)
        }
    }

    @Test
    fun testAddition() {
        val num1 = 12.0
        val num2 = 8.0
        val result = num1 + num2
        assertEquals(20.0, result, 0.0001)
        assertEquals("20.0", formatAnswer(result))
    }

    @Test
    fun testSubtraction() {
        val num1 = 15.0
        val num2 = 5.0
        val result = num1 - num2
        assertEquals(10.0, result, 0.0001)
        assertEquals("10.0", formatAnswer(result))
    }

    @Test
    fun testMultiplication() {
        val num1 = 4.0
        val num2 = 2.5
        val result = num1 * num2
        assertEquals(10.0, result, 0.0001)
        assertEquals("10.0", formatAnswer(result))
    }

    @Test
    fun testDivision() {
        val num1 = 15.0
        val num2 = 3.0
        val result = num1 / num2
        assertEquals(5.0, result, 0.0001)
        assertEquals("5.0", formatAnswer(result))
    }

    @Test
    fun testMockupDefaultAnswerFormat() {
        val zero = 0.0
        assertEquals("0.0", formatAnswer(zero))
        val answerText = String.format("Answer: %s", formatAnswer(zero))
        assertEquals("Answer: 0.0", answerText)
    }
}