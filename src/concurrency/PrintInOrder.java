package concurrency;

import java.util.concurrent.Semaphore;

/** https://leetcode.com/problems/print-in-order/ */

public class PrintInOrder {

    /** Synchronzied on Methods:
     * does require "synchronized" keyword on the methods
     * - .wait()
     * - .notifyAll()
     */
    class FooWithSynchronizedMethod {
        private boolean oneDone;
        private boolean twoDone;

        public FooWithSynchronizedMethod() {
            oneDone = false;
            twoDone = false;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            oneDone = true;
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (!oneDone) {
                wait();
            }
            printSecond.run();
            twoDone = true;
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (!twoDone) {
                wait();
            }
            printThird.run();
        }
    }

    /** Semaphore:
     * does not require "synchronized" keyword
     * - instance of Semaphore class
     * - .release()
     * - .acquire()
     */
    class FooWithSemaphore {
        private Semaphore s2;
        private Semaphore s3;

        public FooWithSemaphore() {
            s2 = new Semaphore(0);
            s3 = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            s2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            s2.acquire();
            printSecond.run();
            s3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            s3.acquire();
            printThird.run();
        }
    }

    class FooWithSynchronizedOnObject {
        private final Object lock;
        private boolean oneDone;
        private boolean twoDone;

        public FooWithSynchronizedOnObject() {
            lock = new Object();
            oneDone = false;
            twoDone = false;
        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock) {
                printFirst.run();
                oneDone = true;
                lock.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lock) {
                while (!oneDone) {
                    lock.wait();
                }
                printSecond.run();
                twoDone = true;
                lock.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lock) {
                while (!twoDone) {
                    lock.wait();
                }
                printThird.run();
            }
        }
    }
}
