package string;

/**
 * https://leetcode.com/problems/repeated-string-match/
 */

public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        int count = 0;
        // sb 加 A until 长度大于等于 B
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
//        if (sb.toString().contains(B)) {
//            return count;
//        }
        if (strstr(sb.toString(), B)) {
            return count;
        }
//        if (sb.append(A).toString().contains(B)) {
//            return ++count;
//        }
        if (strstr(sb.append(A).toString(), B)) {
            return ++count;
        }
        // 如果 长度大于等于 B 后，且再加一个 A 还没有见到 B 的话，那就见不到了
        return -1;
    }

    private boolean strstr(String s, String p) {
        for (int i = 0; i <= s.length() - p.length(); i++) {
            int j = 0;
            while (j < p.length()) {
                if (s.charAt(i + j) != p.charAt(j)) {
                    break;
                } else {
                    j++;
                }
            }
            if (j == p.length()) {
                return true;
            }
        }
        return false;
    }

}
