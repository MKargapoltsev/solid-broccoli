package com.voipfuture.utils;

public class Pauses {
    public static void microsleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
