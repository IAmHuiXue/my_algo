package concurrency.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // recycle the threads
        for (int i = 0; i < 5; i++) {
            Task t = new Task(i);
            executor.execute(t); // execute() to process the runnable task submitted to the executor service
        }
        System.out.println("submitted all tasks");

        // Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted
        executor.shutdown();

        // shutdownNow() terminates the executor service immediately, without awaiting all the tasks submitted
        // to be completed
//        executor.shutdownNow();

        // in order not to directly print "finished all tasks",
        // we can either use a while loop
        while (!executor.isTerminated()) {
            // isTerminated():
            // Returns true if all tasks have completed following shut down.
            // Note that isTerminated is never true unless either shutdown or shutdownNow was called first.
            //  Returns:
            //  true if all tasks have completed following shut down
        }
        // or an awaitTermination() for a specified time period:
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
