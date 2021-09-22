package com.newsum.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockedATM {
    private Lock lock;
    private int balance = 100;

    public LockedATM(){
        lock = new ReentrantLock();
    }

    public int withdraw(int value){
        lock.lock();
        int temp = balance;
        try {
            Thread.sleep(100);
            temp -= value;
            balance = temp;
        } catch (InterruptedException e){
            // swallow exception
        }
        lock.unlock();
        return temp;
    }

    public int deposit(int value){
        lock.lock();
        int temp = balance;
        try {
            Thread.sleep(100);
            temp += value;
            Thread.sleep(300);
            balance = temp;
        } catch (InterruptedException e){
            // swallow exception
        }
        lock.unlock();
        return temp;
    }

    public static void main(String[] args) {

    }
}
