package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** https://leetcode.com/problems/group-shifted-strings/ */

public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        // List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            StringBuilder sb = new StringBuilder();

            // the idea is to find the 相对关系 for each str
            //  abc -> a=0, b=1, c=2 -> pattern=(0-0),(1-0),(2-0) -> 0,1,2
            //  def -> d=3, e=4, f=5 -> pattern=(3-3),(4-3),(5-3) -> 0,1,2

            // edge case:
            //   ba and az
            // we can handle the above by automatically adding 26 if the diff is negative
            // ba -> (1-1),(0-1) -> 0,-1+26 -> 0,25
            // az -> (0-0),(25-0) -> 0,25

            char base = str.charAt(0);
            for (char ch : str.toCharArray()) {
                int c = ch - base < 0 ? ch - base + 26 : ch - base;
                sb.append(c).append('.');
            }
            map.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
