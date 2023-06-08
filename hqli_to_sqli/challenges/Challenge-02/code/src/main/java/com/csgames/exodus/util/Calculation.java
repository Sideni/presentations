package com.csgames.exodus.util;

public class Calculation {

    private Calculation(){}

    public static long currentTimeSeconds() {
        return System.currentTimeMillis() / 1000l;
    }
}
