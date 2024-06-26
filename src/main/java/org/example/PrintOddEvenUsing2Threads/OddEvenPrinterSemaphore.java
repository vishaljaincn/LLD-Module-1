package org.example.PrintOddEvenUsing2Threads;

import java.util.concurrent.Semaphore;

/*
A semaphore controls access to a shared resource through the use of a counter. If the counter is greater than zero, then access is allowed. If it is zero, then access is denied.

Java provides the Semaphore class in the java.util.concurrent package and we can use it to implement the explained mechanism. More details about semaphores can be found here.

We create two threads, an odd thread, and an even thread. The odd thread would print the odd numbers starting from 1, and the even thread will print the even numbers starting from 2.

Both the threads have an object of the SharedPrinter class. The SharedPrinter class will have two semaphores, semOdd and semEven which will have 1 and 0 permits to start with. This will ensure that odd number gets printed first.

We have two methods printEvenNum() and printOddNum(). The odd thread calls the printOddNum() method and the even thread calls the printEvenNum() method.

To print an odd number, the acquire() method is called on semOdd, and since the initial permit is 1, it acquires the access successfully, prints the odd number and calls release() on semEven.

Calling release() will increment the permit by 1 for semEven, and the even thread can then successfully acquire the access and print the even number.
 */
public class OddEvenPrinterSemaphore {
    private final Count count = new Count();
    private static final int MAX = 100;

    // Semaphores for controlling access to odd and even number printing
    private final Semaphore oddSemaphore = new Semaphore(1); // Initially allow the odd thread to run
    private final Semaphore evenSemaphore = new Semaphore(0); // Initially block the even thread

    public static void main(String[] args) {
        OddEvenPrinterSemaphore printer = new OddEvenPrinterSemaphore();

        Thread t1 = new Thread(() -> printer.printOddNumbers());
        Thread t2 = new Thread(() -> printer.printEvenNumbers());

        t1.start();
        t2.start();
    }

    public void printOddNumbers() {
        try {
            while (count.value < MAX) {
                oddSemaphore.acquire(); // Acquire the odd semaphore
                if (count.value < MAX && count.value % 2 != 0) {
                    System.out.println("Thread 1: " + count.value);
                    count.value++;
                }
                evenSemaphore.release(); // Release the even semaphore
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void printEvenNumbers() {
        try {
            while (count.value <= MAX) {
                evenSemaphore.acquire(); // Acquire the even semaphore
                if (count.value <= MAX && count.value % 2 == 0) {
                    System.out.println("Thread 2: " + count.value);
                    count.value++;
                }
                oddSemaphore.release(); // Release the odd semaphore
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

