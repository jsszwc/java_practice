package com.jsszwc.blockqueue;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private volatile boolean running = true;
    private BlockingQueue<String> queue;
    private static AtomicInteger count = new AtomicInteger();
    private String name;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(String name, BlockingQueue<String> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        String data = null;
        Random r = new Random();

        System.out.println("producer...");
        try {
            while(running) {
                System.out.println("produce data");
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                data = name + ": data-" + count.incrementAndGet();
                System.out.println("data: " + data);
                if(!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("put data fail: " + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("exit producer");
        }
    }

    public void stop() {
        running = false;
    }
}
