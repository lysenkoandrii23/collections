package com.lysenko.andrii;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example {
    private int value = 0;
    //getNextValue()
    private final Lock lock = new ReentrantLock();

//    synchronized int getNextValue() {
//        return ++value;
//    }

    int getNextValue() {
        lock.lock();
        try {
            return ++value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Example e = new Example();
        System.out.println(e.getNextValue());
        System.out.println(e.getNextValue());
        System.out.println(e.getNextValue());
        new Thread() {
            public void run() {
                System.out.println(e.getNextValue());
            }
        }.start();
    }
}

