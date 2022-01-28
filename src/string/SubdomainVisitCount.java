package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** https://leetcode.com/problems/subdomain-visit-count/ */

public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        // each domain is [rep d1.d2.d3] or [rep d1.d2]
        Map<String, Integer> map = new HashMap<>();
        for (String cp : cpdomains) {
            String[] splits = cp.split(" ");
            int curFreq = Integer.parseInt(splits[0]);
            String[] subs = splits[1].split("\\."); // not "." !
            String curSub = "";
            for (int i = subs.length - 1; i >= 0; i--) {
                if (i == subs.length - 1) {
                    curSub += subs[i];
                } else {
                    curSub = subs[i] + "." + curSub;
                }
                map.put(curSub, map.getOrDefault(curSub, 0) + curFreq);
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;
    }
}
