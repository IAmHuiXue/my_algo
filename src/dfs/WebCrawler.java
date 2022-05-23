package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebCrawler {
    // This is the HtmlParser's API interface.
    // You should not implement it, or speculate about its implementation
      interface HtmlParser {
          List<String> getUrls(String url);
      }

    public List<String> crawl1(String startUrl, HtmlParser htmlParser) {
        Set<String> res = new HashSet<>();
        String hostname = startUrl.split("/")[2];
        dfs1(startUrl, res, htmlParser, hostname);
        return new ArrayList<>(res);
    }

    void dfs1(String startUrl, Set<String> res, HtmlParser htmlParser, String hostname) {
        if (res.contains(startUrl)) {
            return;
        }

        if (!startUrl.split("/")[2].equals(hostname)) {
            return;
        }

        res.add(startUrl);

        List<String> neis = htmlParser.getUrls(startUrl);
        for (String nei : neis) {
            dfs1(nei, res, htmlParser, hostname);
        }
    }

    public List<String> crawl2(String startUrl, HtmlParser htmlParser) {
        Set<String> res = new HashSet<>();
        String hostname = startUrl.split("/")[2];
        dfs2(startUrl, res, htmlParser, hostname);
        return new ArrayList<>(res);
    }

    void dfs2(String startUrl, Set<String> res, HtmlParser htmlParser, String hostname) {
        if (!res.add(startUrl)) {
            return;
        }

        List<String> neis = htmlParser.getUrls(startUrl);
        for (String nei : neis) {
            if (nei.split("/")[2].equals(hostname)) {
                dfs2(nei, res, htmlParser, hostname);
            }
        }
    }
}
