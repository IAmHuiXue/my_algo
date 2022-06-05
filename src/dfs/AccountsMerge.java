package dfs;

import java.util.*;

/** <a href="https://leetcode.com/problems/accounts-merge/">...</a> */

public class AccountsMerge {

    // finding connected components of this graph
    HashSet<String> visited = new HashSet<>();
    Map<String, List<String>> adjacent = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accountList) {
        for (List<String> account : accountList) {
            int accountSize = account.size();

            // Building adjacency list
            // Trick: do not use Name to represent the graph node as there could be duplicates in this problem
            // Choose one of the emails as the node:
            // the rest of the emails are in this email's neighbour list, and this email is in their neighbour's lists
            String accountFirstEmail = account.get(1);
            for (int j = 2; j < accountSize; j++) {
                String accountEmail = account.get(j);
                adjacent.computeIfAbsent(accountFirstEmail, k -> new ArrayList<>()).add(accountEmail);
                adjacent.computeIfAbsent(accountEmail, k -> new ArrayList<>()).add(accountFirstEmail);
            }
        }

        // Traverse over all th accounts to store components
        List<List<String>> res = new ArrayList<>();
        for (List<String> account : accountList) {
            String accountName = account.get(0);
            String accountFirstEmail = account.get(1);

            // If email is visited, then it's a part of different component
            // Hence perform DFS only if email is not visited yet
            if (!visited.contains(accountFirstEmail)) {
                List<String> curRes = new ArrayList<>();
                // Adding account name at the 0th index
                curRes.add(accountName);
                DFS(curRes, accountFirstEmail);
                Collections.sort(curRes.subList(1, curRes.size()));
                res.add(curRes);
            }
        }

        return res;
    }

    private void DFS(List<String> mergedAccount, String email) {
        visited.add(email);
        // Add the email vector that contains the current component's emails
        mergedAccount.add(email);

        for (String neighbor : adjacent.getOrDefault(email, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                DFS(mergedAccount, neighbor);
            }
        }
    }
}
