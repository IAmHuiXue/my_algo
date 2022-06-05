package graph;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/evaluate-division/">https://leetcode.com/problems/evaluate-division/</a>
 */

public class EvaluateDivision {
    static class Cell {
        String letter;
        double value;

        Cell(String l, double v) {
            letter = l;
            value = v;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Cell>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> e = equations.get(i);
            String e1 = e.get(0);
            String e2 = e.get(1);
            graph.computeIfAbsent(e1, k -> new ArrayList<>()).add(new Cell(e2, values[i]));
            //graph.computeIfAbsent(e1, k -> new ArrayList<>()).add(new Cell(e1, 1.0));
            graph.computeIfAbsent(e2, k -> new ArrayList<>()).add(new Cell(e1, 1 / values[i]));
            //graph.computeIfAbsent(e2, k -> new ArrayList<>()).add(new Cell(e2, 1.0));
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < res.length; i++) {
            List<String> q = queries.get(i);
            if (!graph.containsKey(q.get(0)) || !graph.containsKey(q.get(1))) {
                res[i] = -1.0;
                continue;
            }
            if (q.get(0).equals(q.get(1))) {
                res[i] = 1.0;
                continue;
            }
//            res[i] = bfs(q.get(0), q.get(1), graph);
            res[i] = dfs(q.get(0), q.get(1), graph, new HashSet<>());
        }
        return res;
    }

    double bfs(String start, String end, Map<String, List<Cell>> graph) {
        Set<String> visited = new HashSet<>();
        Queue<Cell> q = new ArrayDeque<>();
        q.offer(new Cell(start, 1.0));
        visited.add(start);
        while (!q.isEmpty()) {
            Cell cur = q.poll();
            if (cur.letter.equals(end)) {
                return cur.value;
            }
            for (Cell nei : graph.getOrDefault(cur.letter, new ArrayList<>())) {
                if (visited.add(nei.letter)) {
                    q.offer(new Cell(nei.letter, cur.value * nei.value));
                }
            }
        }
        return -1.0;
    }

    double dfs(String cur, String end, Map<String, List<Cell>> graph, Set<String> visited) {
        visited.add(cur);
        for (Cell nei : graph.getOrDefault(cur, new ArrayList<>())) {
            if (nei.letter.equals(end)) {
                return nei.value;
            }
            if (visited.contains(nei.letter)) {
                continue;
            }
            double res = dfs(nei.letter, end, graph, visited);
            if (res != -1.0) {
                return nei.value * res;
            }
        }
        return -1.0;
    }
}
