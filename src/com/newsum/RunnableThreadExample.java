package com.newsum;

public class RunnableThreadExample implements Runnable{
    public int count = 0;

    @Override
    public void run() {
        System.out.println("RunnableThread starting.");

        try {
            while (count < 5){
                Thread.sleep(1000);;
                count++;
            }
        } catch (InterruptedException e){
            System.out.println("RunnableThread interrupted.");
        }
        System.out.println("RunnableThread terminating");
    }

    public static void main(String[] args) {
        RunnableThreadExample instance = new RunnableThreadExample();
        Thread thread = new Thread(instance);
        thread.start();

        // waits until above thread counts to 5 (slowly)
        while (instance.count != 5){
            try{
                Thread.sleep(250);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
