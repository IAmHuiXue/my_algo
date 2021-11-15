package concurrency;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class AirlineTicketMultiThread {
    Map<String, List<Point>> pricesMap;
    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public AirlineTicketMultiThread() {
        pricesMap = buildPricesMap(getAirlines());
    }
    // a list of cities:
    // TOKYO, BEIJING, NYC, TORONTO, DELHI, MUMBAI, MOSCOW, PARIS, LONDON, LA, BOSTON, BARCELONA
    // TOKYO - BEIJING - DELHI - LONDON - TORONTO - NYC
    // TOKYO - DELHI - MUMBAI - MOSCOW - PARIS - LA - NYC

    // TOKYO - MUMBAI - DELHI - MOSCOW - BARCELONA - PARIS - BOSTON - NYC
    // TOKYO - DELHI - BEIJING - MOSCOW - LONDON - TORONTO - LA - NYC
    // TOKYO - MOSCOW - BARCELONA - TORONTO - NYC
    public static void main(String[] args) {
        // ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        // Set<String> visited = ConcurrentHashMap.newKeySet();

        // AirlineTicketMultiThread test = new AirlineTicketMultiThread();
        // List<Integer> result = Collections.synchronizedList(new ArrayList<Integer>());
        // Set<String> set = new HashSet<>();
        // test.getLowPriceOneSet("TOKYO", "NYC", visited, result, 0);
        // // test.getAllPrices("TOKYO", "NYC", 0, "TOKYO", new HashSet<>());
        // System.out.println(result);

        int res = 2780;
        List<Long> futureTimes = new ArrayList<>();
        List<Long> oldRunTimes = new ArrayList<>();
        List<Long> oldRunOneSetTimes = new ArrayList<>();
        List<Long> syncRunTimes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            // System.out.println("###############");
            // run();
            long now = System.currentTimeMillis();
            int f = futureRun();
            if (f != res) {
                System.out.println("futureRun - Wrong!!!!!!!" + f);
            }
            long after = System.currentTimeMillis();
            long future = after - now;

            now = System.currentTimeMillis();
            if (run() != res) {
                System.out.println("run - Wrong!!!!!!!");
            }
            after = System.currentTimeMillis();
            long oldRun = after - now;

            now = System.currentTimeMillis();
            if (oneSetRun() != res) {
                System.out.println("oneSetRun - Wrong!!!!!!!");
            }
            after = System.currentTimeMillis();
            long oneSetRun = after - now;

            now = System.currentTimeMillis();
            int syncRes = syncRun();
            if (syncRes != res) {
                System.out.println("syncRun - Wrong!!!!!!!" + syncRes);
            }
            // System.out.println("syncRun -" + syncRes);
            after = System.currentTimeMillis();
            long syncRun = after - now;

            futureTimes.add(future);
            oldRunOneSetTimes.add(oneSetRun);
            oldRunTimes.add(oldRun);
            syncRunTimes.add(syncRun);
            // System.out.println("Future - " + future + ", oldRun - " + oldRun + ", oneSetRun - " + oneSetRun);
            // System.out.println("###############");
        }

        System.out.println("Future - " + futureTimes.stream().mapToDouble(val -> val).average() +
                ", oldRun - " + oldRunTimes.stream().mapToDouble(val -> val).average() +
                ", oneSetRun - " + oldRunOneSetTimes.stream().mapToDouble(val -> val).average() +
                ", syncRunTimes - " + syncRunTimes.stream().mapToDouble(val -> val).average()
        );
    }

    public static int run() {
        AirlineTicketMultiThread test = new AirlineTicketMultiThread();
        List<Integer> result = Collections.synchronizedList(new ArrayList<>());
        Set<String> set = new HashSet<>();
        test.getLowPrice("TOKYO", "NYC", set, result, 0);
        // System.out.println(result);
        return result.get(0);
    }

    public static int futureRun() {
        Set<String> visited = ConcurrentHashMap.newKeySet();
        AirlineTicketMultiThread test = new AirlineTicketMultiThread();
        // return test.getLowPriceWithFuture("TOKYO", "NYC", visited);
        return test.getLowPriceWithFuture("TOKYO", "NYC", visited);
    }

    public static int syncRun() {
        AirlineTicketMultiThread test = new AirlineTicketMultiThread();
        return test.getLowPriceSync("TOKYO", "NYC", new HashMap<>());
    }

    public static int oneSetRun() {
        Set<String> visited = ConcurrentHashMap.newKeySet();
        AirlineTicketMultiThread test = new AirlineTicketMultiThread();
        List<Integer> result = Collections.synchronizedList(new ArrayList<>());

        test.getLowPriceOneSet("TOKYO", "NYC", visited, result, 0);
        // System.out.println(result);
        return result.get(0);
    }

    public void getLowPrice(String src, String dest, Set<String> visited, List<Integer> result, int currentPrice) {
        if (src.equals(dest)) {
            result.add(currentPrice);
            return;
        }

        if (visited.contains(src)) {
            return;
        }

        List<Point> lines = pricesMap.get(src);
        List<Integer> priceList = Collections.synchronizedList(new ArrayList<>());
        Set<String> newSet = new HashSet<>(visited);
        newSet.add(src);
        List<Thread> threads = new ArrayList<>();

        for (Point line : lines) {
            threads.add(new Thread(() -> getLowPrice(line.dest, dest, newSet, priceList, line.price)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collections.sort(priceList);
        if (priceList.size() > 0) result.add(priceList.get(0) + currentPrice);
    }

    public void getLowPriceOneSet(String src, String dest, Set<String> visited, List<Integer> result, int currentPrice) {
        if (src.equals(dest)) {
            result.add(currentPrice);
            return;
        }

        if (visited.contains(src)) {
            return;
        }

        List<Point> lines = pricesMap.get(src);
        List<Integer> priceList = Collections.synchronizedList(new ArrayList<>());
        visited.add(src);
        List<Thread> threads = new ArrayList<>();

        for (Point line : lines) {
            threads.add(new Thread(() -> getLowPrice(line.dest, dest, visited, priceList, line.price)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collections.sort(priceList);
        if (priceList.size() > 0) result.add(priceList.get(0) + currentPrice);
    }

    public int getLowPriceWithFuture(String src, String dest, Set<String> visited) {
        if (src.equals(dest)) {
            return 0;
        }

        if (visited.contains(src)) {
            return -1;
        }

        List<Point> lines = pricesMap.get(src);
        Set<String> newSet = new HashSet<>(visited);
        newSet.add(src);
        // visited.add(src);
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (Point line : lines) {
            CompletableFuture<Integer> myFuture = new CompletableFuture<>();
            futures.add(myFuture);
            new Thread(() -> {
                int cost = getLowPriceWithFuture(line.dest, dest, newSet);
                if (cost >= 0) {
                    myFuture.complete( cost + line.price);
                } else {
                    myFuture.complete(-1);
                }
            }).start();
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        int min = Integer.MAX_VALUE;
        for (CompletableFuture<Integer> f : futures) {
            Integer result = -1;
            try {
                result = f.get();
            } catch (Exception  e) {
                e.printStackTrace();
            }
            if (result >= 0 && result < min) {
                min = result;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // this gives out wrong result irregularly
    public int getLowPriceWithFutureOneSet(String src, String dest, Set<String> visited) {
        if (src.equals(dest)) {
            return 0;
        }

        if (visited.contains(src)) {
            return -1;
        }

        List<Point> lines = pricesMap.get(src);
        // Set<String> newSet = new HashSet<>(visited);
        // newSet.add(src);
        visited.add(src);
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (Point line : lines) {
            CompletableFuture<Integer> myFuture = new CompletableFuture<>();
            futures.add(myFuture);
            new Thread(() -> {
                int cost = getLowPriceWithFuture(line.dest, dest, visited);
                if (cost >= 0) {
                    myFuture.complete( cost + line.price);
                } else {
                    myFuture.complete(-1);
                }
            }).start();
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        int min = Integer.MAX_VALUE;
        for (CompletableFuture<Integer> f : futures) {
            Integer result = -1;
            try {
                result = f.get();
            } catch (Exception  e) {
                e.printStackTrace();
            }
            if (result >= 0 && result < min) {
                min = result;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void getAllPrices(String src, String dest, int curr, String path, Set<String> visited) {
        if (visited.contains(src)) return;
        if (src.equals(dest)) {
            System.out.println(path + ":  " + curr);
            return;
        }
        visited.add(src);
        for (Point line : pricesMap.get(src)) {
            getAllPrices(line.dest, dest, curr + line.price, path + "-" + line.dest, visited);
        }
        visited.remove(src);
    }

    public int getLowPriceSync(String src, String dest, Map<String, Integer> map) {
        if (src.equals(dest)) return 0;
        if (map.containsKey(src)) {
            return map.get(src);
        }
        map.put(src, Integer.MAX_VALUE);
        int min = Integer.MAX_VALUE;
        for (Point line : pricesMap.get(src)) {
            int cost = getLowPriceSync(line.dest, dest, map);
            if (cost < min && cost + line.price < min) {
                min = cost + line.price;
            }
        }
        map.put(src, min);
        // map.remove(src);
        return min;
    }

    public List<String> getAirlines() {
        // in reality, prices differ when destination & departure switches
        return Arrays.asList(
                "BEIJING-DELHI, 800", "DELHI-LONDON, 780", "TOKYO-BEIJING, 300", "LONDON-TORONTO, 1300", "TORONTO-NYC, 250",
                "TOKYO-DELHI, 450", "DELHI-MUMBAI, 760", "MUMBAI-MOSCOW, 580", "MOSCOW-PARIS, 880", "PARIS-LA, 1760", "LA-NYC, 320",
                "TOKYO-MUMBAI, 770", "MUMBAI-DELHI, 930", "DELHI-MOSCOW, 840", "MOSCOW-BARCELONA, 990", "BARCELONA-PARIS, 310",
                "PARIS-BOSTON, 1030", "BOSTON-NYC, 420", "DELHI-BEIJING, 580", "BEIJING-MOSCOW, 720", "MOSCOW-LONDON, 1300",
                "TORONTO-LA, 580", "TOKYO-MOSCOW, 840", "BARCELONA-TORONTO, 1420"
        );
    }

    public Map<String, List<Point>> buildPricesMap(List<String> lines) {
        Map<String, List<Point>> map = new HashMap<>();
        for (String line : lines) {
            String[] values = line.split(",");
            int price = Integer.parseInt(values[1].trim());
            String[] cities = values[0].split("-");
            Point info = new Point(price, cities[1]);
            if (map.containsKey(cities[0])) {
                // already has this city
                map.get(cities[0]).add(info);
            } else {
                List<Point> list = new ArrayList<>();
                list.add(info);
                map.put(cities[0], list);
            }
        }
        return map;
    }

    static class Point {
        int price;
        String dest;

        public Point(int p, String d) {
            price = p;
            dest = d;
        }
    }
}
