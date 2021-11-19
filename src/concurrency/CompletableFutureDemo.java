package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // future class is a variable placeholder that is able to carry the result calculated in each Thread instance
        // introducing this because Java Thread run() is a void type

        // 1. create a list of Future instances, <> contains the type of the variable that the Future should bring
        List<CompletableFuture<Double>> futures = new ArrayList<>();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            // 2. create a Future instance and add it into the result list
            CompletableFuture<Double> future = new CompletableFuture<>();
            futures.add(future);
            // 3. create a thread and invoke complete() of Future class (the type of the return need to be the same
            // as the type of the Future class
            Thread t = new Thread(() -> future.complete(Math.random() * Math.random()));
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // join all the Future instances
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // in the end, get the result from each future in the future list
        // by calling future.get()
        for (CompletableFuture<Double> f : futures) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

