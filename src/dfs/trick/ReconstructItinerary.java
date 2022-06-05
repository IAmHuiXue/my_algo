package dfs.trick;

import java.util.*;

/** <a href="https://leetcode.com/problems/reconstruct-itinerary/">...</a> */

public class ReconstructItinerary {

    /** 使用 postorder traversal 的一个好例子 */

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>(); // sorted by lexical order
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        List<String> res = new LinkedList<>();
        dfs(res, graph, "JFK");
        return res;
    }

    private void dfs(List<String> res, Map<String, PriorityQueue<String>> graph, String cur) {
        PriorityQueue<String> neis = graph.getOrDefault(cur, new PriorityQueue<>());
        while (!neis.isEmpty()) {
            /** 题设保证了一定有结果，所以就直接把 edge 从 pq 里删除，每个 edge 只用一次 */
            dfs(res, graph, neis.poll());
        }
        res.add(0, cur);
    }

    // time: O(V + E*log(E))
    // traverse all the V + E, and store and poll all E in and from pq
}
