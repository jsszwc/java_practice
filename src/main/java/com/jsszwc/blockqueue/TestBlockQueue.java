package com.jsszwc.blockqueue;

import java.util.concurrent.*;

public class TestBlockQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        Producer producer1 = new Producer("p1", queue);
        Producer producer2 = new Producer("p2", queue);
        Producer producer3 = new Producer("p3", queue);

        Consumer consumer = new Consumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);

        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(3000);
        service.shutdown();
    }
}
