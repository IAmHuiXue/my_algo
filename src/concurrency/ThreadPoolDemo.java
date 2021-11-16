package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // recycle the threads
        for (int i = 0; i < 5; i++) {
            Task t = new Task(i);
            executor.execute(t); // execute() to process the runnable task submitted to the executor service
        }
        System.out.println("submitted all tasks");
        // when we have all the tasks submitted, we need to let the service shut down when it
        // completes all the tasks, so that it will not keep waiting for tasks.
        executor.shutdown();

        // terminate teh executor service immediately, without awaiting all the tasks submitted
        // to be completed
//        executor.shutdownNow();

        // in order not to directly print "finished all tasks",
        // we can either use a while loop
        while (!executor.isTerminated()) {

        }
        // or a awaitTermination() for a specified time period:
//        try {
//            executor.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("finished all tasks");
    }
}

class Task implements Runnable {
    int message;

    public Task(int message) {
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " [received] Message = " + message);
        respondToMessage(); // make thread sleep to simulate doing some work
        System.out.println(Thread.currentThread().getName() + " (Done) Processing Message = " + message);
    }

    private void respondToMessage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println("Unable to process the message" + message);
        }
    }
}
