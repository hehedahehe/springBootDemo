package com.cesuokanc.multithread.counter;

/**
 * @author liruibo
 * @date 2018/7/18
 */
public class CounterSyn {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public int value() {
        return c;
    }

    public static void main(String[] args)throws InterruptedException{
        long begin = System.currentTimeMillis();

        CounterSyn counter = new CounterSyn();
        int times = 1000000;
        Thread t1 = new Thread(()->{
           for(int i=0;i<times;i++){
               counter.increment();
           }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<times;i++){
                counter.decrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println((end-begin)+"====>"+counter.value());

    }
}
