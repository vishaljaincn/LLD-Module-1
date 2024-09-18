package org.example.Print123with3diffThreadsandsoontill10;

import java.util.concurrent.Semaphore;

public class Num1 implements Runnable {
    Semaphore s1;
    Semaphore s2;
    Semaphore s3;
    Count count;

    Num1(Semaphore s1, Semaphore s2, Semaphore s3, Count count) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.count = count;
    }

    public void run() {
        try {
            while (count.value <= 10) {
                s1.acquire();
                if (count.value <= 10) {
                    System.out.println(Thread.currentThread().getName() + " : " + count.value);
                    count.value += 1;
                }
                s2.release();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
