package com.newsum;

import java.io.FileInputStream;
import java.util.function.Function;
import java.util.function.Predicate;

public class FizzBuzzThread extends Thread{
    private static Object lock = new Object();
    protected static int current = 1;
    private int max;
    private Predicate<Integer> validate;
    private Function<Integer,String> printer;
    int x = 1;

    public FizzBuzzThread(Predicate<Integer> validate, Function<Integer,String> printer, int max){
        this.validate = validate;
        this.printer = printer;
        this.max = max;
    }

    @Override
    public void run(){
        while (true){
            synchronized (lock){
                if (current > max){
                    return;
                }
                if (validate.test(current)){
                    System.out.println(printer.apply(current));
                    current++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 100;
        Thread[] threads = {
          new FizzBuzzThread(i -> i % 3 == 0 && i % 5 == 0,i -> "FizzBuzz",n),
          new FizzBuzzThread(i -> i % 3 == 0, i -> "Fizz",n),
          new FizzBuzzThread(i -> i % 5 == 0, i -> "Buzz",n),
          new FizzBuzzThread(i -> i % 3 != 0 && i % 5 != 0, i -> Integer.toString(i),n)
        };

        for (Thread t : threads){
            t.start();
        }
    }
}
