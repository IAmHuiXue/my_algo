package dfs;

import java.util.*;

/** https://leetcode.com/problems/accounts-merge/ */

public class AccountsMerge {

    // finding connected components of this graph

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, List<String>> emailToEmail = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToEmail.computeIfAbsent(email, k -> new ArrayList<>());

                // we do not have to connect all neighbors to the current email,
                // as long as we make they can make a component for dfs later
                if (i > 1) {
                    String preEmail = account.get(i - 1);
                    emailToEmail.get(email).add(preEmail);
                    emailToEmail.get(preEmail).add(email);
                }
                emailToName.putIfAbsent(email, name);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> result = new LinkedList<>(); // need to add to head
        for (String email : emailToEmail.keySet()) {
            if (visited.add(email)) {
                List<String> list = new ArrayList<>();
                dfs(list, visited, email, emailToEmail);
                list.sort((s1, s2) -> s1.compareTo(s2));
                list.add(0, emailToName.get(email));
                result.add(list);
            }
        }
        return result;
    }

    private void dfs(List<String> list, Set<String> visited, String email, Map<String, List<String>> emailToEmail) {
        list.add(email);
        for (String nei : emailToEmail.get(email)) {
            if (visited.add(nei)) {
                dfs(list, visited, nei, emailToEmail);
            }
        }
    }
}
