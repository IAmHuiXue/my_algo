package concurrency;

import bfs.WebCrawler;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.com/problems/web-crawler-multithreaded/
 */

public class WebCrawlerMultithreaded extends WebCrawler {
    class UsingConcurrentLinkedQueue {
        public List<String> crawl(String startUrl, HtmlParser htmlParser) {
            String hostName = getHostname(startUrl);
            List<String> res = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
            Deque<Future<String>> tasks = new ArrayDeque<>();

            queue.offer(startUrl);

            // Create a thread pool of 4 threads to perform I/O operations.
            ExecutorService executor = Executors.newFixedThreadPool(4, r -> {
                Thread t = new Thread(r);
                // LeetCode doesn't allow executor.shutdown().
                // Use daemon threads so the program can exit.
                t.setDaemon(true);
                return t;
            });

            while (true) {
                String url = queue.poll();
                if (url != null) {
                    if (getHostname(url).equals(hostName) && !visited.contains(url)) {
                        res.add(url);
                        visited.add(url);
                        // Use a thread in thread pool to fetch new URLs and put them into the queue.
                        tasks.add((Future<String>) executor.submit(() -> {
                            List<String> newUrls = htmlParser.getUrls(url);
                            for (String newUrl : newUrls) {
                                queue.offer(newUrl);
                            }
                        }));
                    }
                } else {
                    if (!tasks.isEmpty()) {
                        // Wait for the next task to complete, which may supply new URLs into the queue.
                        Future nextTask = tasks.poll();
                        try {
                            nextTask.get();
                        } catch (InterruptedException | ExecutionException e) {
                        }
                    } else {
                        // Exit when all tasks are completed.
                        break;
                    }
                }
            }
            return res;
        }
    }

    class UsingThreadPool {
        // The tasks are submitted to the pool recursively. The main challenge was to stop executor service when all
        // tasks are done. I'm just using Atomic Integer to count active tasks.
        private ExecutorService executor = Executors.newFixedThreadPool(6); // !
        private AtomicInteger activeTasks = new AtomicInteger(); // !
        private HtmlParser htmlParser;
        private final Set<String> visited = Collections.synchronizedSet(new HashSet<>()); // !
        private String hostname;

        private class Task implements Runnable {
            String url;

            public Task(String url) {
                this.url = url;
            }

            @Override
            public void run() {
                for (String link : htmlParser.getUrls(url)) {
                    if (link.split("/")[2].equals(hostname) && visited.add(link)) {
                        activeTasks.incrementAndGet();
                        executor.execute(new Task(link));
                    }
                }
                activeTasks.decrementAndGet();
            }
        }

        public List<String> crawl(String startUrl, HtmlParser htmlParser) {
            this.htmlParser = htmlParser;
            this.hostname = startUrl.split("/")[2];
            visited.add(startUrl);
            activeTasks.set(1);
            executor.execute(new Task(startUrl));
            while (activeTasks.get() > 0) {
                try {
                    Thread.sleep(80);
                } catch (Exception e) {

                }
            }
            executor.shutdownNow();
            return new ArrayList<>(visited);
        }
    }

    class UsingConcurrentHashMap {
        public List<String> crawl(String startUrl, HtmlParser htmlParser) {

            // find hostname
            int index = startUrl.indexOf('/', 7);
            String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

            // multi-thread
            Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
            crawler.map = new ConcurrentHashMap<>(); // reset result as static property belongs to class, it will go through all the test cases
            crawler.result = crawler.map.newKeySet();
            Thread thread = new Thread(crawler);
            thread.start();

            crawler.joinThread(thread); // wait for thread to complete
            return new ArrayList<>(crawler.result);
        }

        class Crawler implements Runnable {
            String startUrl;
            String hostname;
            HtmlParser htmlParser;
            public ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            public Set<String> result = map.newKeySet();

            public Crawler(String startUrl, String hostname, HtmlParser htmlParser) {
                this.startUrl = startUrl;
                this.hostname = hostname;
                this.htmlParser = htmlParser;
            }

            @Override
            public void run() {
                if (this.startUrl.startsWith(hostname) && !this.result.contains(this.startUrl)) {
                    this.result.add(this.startUrl);
                    List<Thread> threads = new ArrayList<>();
                    for (String s : htmlParser.getUrls(startUrl)) {
                        if (result.contains(s)) continue;
                        Crawler crawler = new Crawler(s, hostname, htmlParser);
                        Thread thread = new Thread(crawler);
                        thread.start();
                        threads.add(thread);
                    }
                    for (Thread t : threads) {
                        joinThread(t); // wait for all threads to complete
                    }
                }
            }

            public void joinThread(Thread thread) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
