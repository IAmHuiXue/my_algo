package bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/web-crawler/
 */

public class WebCrawler {

    // This is the HtmlParser's API interface.
    // You should not implement it, or speculate about its implementation
    interface HtmlParser {
        List<String> getUrls(String url);
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        List<String> res = new ArrayList<>();
        Queue<String> q = new ArrayDeque<>();
        String hostname = getHostname(startUrl);
        q.offer(startUrl);
        Set<String> visited = new HashSet<>();
        visited.add(startUrl);

        while (!q.isEmpty()) {
            String cur = q.poll();
            res.add(cur);
            for (String nei : htmlParser.getUrls(cur)) {
                if (getHostname(nei).equals(hostname) && visited.add(nei)) {
                    q.offer(nei);
                }
            }
        }
        return res;
    }

    private String getHostname(String url) {

        String[] portions = url.split("/");
        return portions[2];

//        StringBuilder sb = new StringBuilder();
//        for (int i = 7; i < url.length(); i++) {
//            char ch = url.charAt(i);
//            if (ch == '/') {
//                break;
//            }
//            sb.append(ch);
//        }
//        return sb.toString();
    }
}
