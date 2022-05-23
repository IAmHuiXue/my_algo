package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/isomorphic-strings/">https://leetcode.com/problems/isomorphic-strings/</a>
 */

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        // map to build the map between s and t and the same position
        // set adds the char from t to indicate the char has been mapped
        // to avoid case like abbc, tllt
        Map<Character, Character> relation = new HashMap<>();
        Set<Character> added = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (!relation.containsKey(sc)) {
                if (added.contains(tc)) {
                    return false;
                }
                relation.put(sc, tc);
                added.add(tc);
            } else if (relation.get(sc) != tc) {
                return false;
            }
        }
        return true;
    }
}
