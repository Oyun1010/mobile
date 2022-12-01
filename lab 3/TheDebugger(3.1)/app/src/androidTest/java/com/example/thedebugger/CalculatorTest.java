/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.thedebugger;


import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import androidx.test.filters.SmallTest;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public  void addTwoNumbersNegative() {
        double resultAdd = mCalculator.add(-1d, 2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }
    @Test
    public  void addTwoNumbersFloats() {
        double resultAdd = mCalculator.add(1.111f, 1.111f);
        assertThat(resultAdd, Matchers.closeTo(2.222, 0.01));
    }

    @Test
    public  void subTwoNumbers() {
        double resultSub = mCalculator.sub(2d, 1d);
        assertThat(resultSub, is(equalTo(1d)));
    }
    @Test
    public void subWorksWithNegativeResults() {
        double resultSub = mCalculator.sub(-2d, 1d);
        assertThat(resultSub, is(equalTo(-3d)));
    }
    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(15d, 10d);
        assertThat(resultMul, is(equalTo(150d)));
    }
    @Test
    public void mulTwoNumbersZero() {
        double resultMul = mCalculator.mul(0d, 10d);
        assertThat(resultMul, is(equalTo(0d)));
    }
    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(15d, 5d);
        assertThat(resultDiv, is(equalTo(3d)));
    }
    @Test
    public void divTwoNumbersZero() {
        double resultDiv = mCalculator.div(32d,0);
        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
    }
    @Test
    public void powNumber() {
        double resultPow = mCalculator.pow(2d, 2d);
        assertThat(resultPow, is(equalTo(4d)));
    }
    @Test
    public void powNegativeResults() {
        double res = mCalculator.pow(-5d, 3d);
        assertThat(res, is(equalTo(-125d)));
    }
    @Test
    public void powNegativeResults2() {
        double res = mCalculator.pow(4d, -2d);
        assertThat(res, is(equalTo(-0.0625f)));
    }
    @Test
    public void powNumbersZero() {
        double resultDiv = mCalculator.pow(7d,0);
        assertThat(resultDiv, is(equalTo(1d)));
    }
    @Test
    public void powTest6() {
        double resultDiv = mCalculator.pow(0d,-1d);
        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
    }
    @Test
    public void powTest7() {
        double resultDiv = mCalculator.pow(0d,-5d);
        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
    }



}