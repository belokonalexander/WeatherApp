package com.example.alexander.weatherapp;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */




public class ExampleUnitTest {

    class Item{
        int num;
        int swaps = 2;

        Item(int i){
            num = i;
        }

        public int swapped(){
            swaps--;
            return swaps;
        }

        @Override
        public String toString() {
            return num+"["+swaps+"]";
        }
    }


    @Test
    public void addition_isCorrect() throws Exception {

    }

}