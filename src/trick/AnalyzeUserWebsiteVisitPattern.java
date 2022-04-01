package trick;

import java.util.*;

/**
 * https://leetcode.com/problems/analyze-user-website-visit-pattern/
 */

public class AnalyzeUserWebsiteVisitPattern {
    static class Visit {
        String userName;
        int timestamp;
        String website;

        Visit(String u, int t, String w) {
            userName = u;
            timestamp = t;
            website = w;
        }

    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        // Convert all the entry as visit object to ease of understand
        List<Visit> visitList = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            visitList.add(new Visit(username[i], timestamp[i], website[i]));
        }

        // Sort all the visit entries using their timestamp
        Collections.sort(visitList, (v1, v2) -> Integer.compare(v1.timestamp, v2.timestamp));

        //Collect list of websites for each user
        Map<String, List<String>> userWebSitesMap = new HashMap<>();
        for (Visit v : visitList) {
            userWebSitesMap.computeIfAbsent(v.userName, k -> new ArrayList<>()).add(v.website);
        }
        int max = 0;
        List<String> result = new ArrayList<>();
        Map<List<String>, Integer> seqUserFreMap = new HashMap<>();
        // Now get all the values of all the users
        for (List<String> websitesList : userWebSitesMap.values()) {
            if (websitesList.size() >= 3) { // no need to consider less than 3 entries of website visited by user
                Set<List<String>> sequencesSet = generate3Seq(websitesList);
                // Now update the frequency of the sequence ( increment by 1 for 1 user)
                for (List<String> seq : sequencesSet) {
                    int freq = seqUserFreMap.getOrDefault(seq, 0);
                    seqUserFreMap.put(seq, freq++ + 1);
                    // we update the result as we go
                    if (max < freq) {
                        max = freq;
                        result = seq;
                        // List does not have compareTo(), so convert it to string for comparison.
                    } else if (max == freq && seq.toString().compareTo(result.toString()) < 0) {
                        result = seq;
                    }
                }
            }
        }
        return result;

//        List<Map.Entry<List<String>, Integer>> list = new ArrayList<>(seqUserFreMap.entrySet());
//        list.sort((a, b) -> {
//            int diff = Integer.compare(b.getValue(), a.getValue());
//            if (diff == 0) {
//                return a.getKey().toString().compareTo(b.getKey().toString());
//            }
//            return diff;
//        });
//        return list.get(0).getKey();

    }

    // It will not return duplicate seq for each user that's why we are using Set
    private Set<List<String>> generate3Seq(List<String> websitesList) { // n^3 to find all pairs
        Set<List<String>> setOfListSeq = new HashSet<>();
        for (int i = 0; i < websitesList.size() - 2; i++) {
            for (int j = i + 1; j < websitesList.size() - 1; j++) {
                for (int k = j + 1; k < websitesList.size(); k++) {
                    List<String> list = new ArrayList<>();
                    list.add(websitesList.get(i));
                    list.add(websitesList.get(j));
                    list.add(websitesList.get(k));
                    setOfListSeq.add(list);
                }
            }
        }
        return setOfListSeq;
    }
}
