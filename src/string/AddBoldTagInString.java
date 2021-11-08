package string;

import sun.jvm.hotspot.utilities.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/add-bold-tag-in-string/
 */

//     you find the index of each string in dict, convert to an interval, you will get

//    [[0, 3], [1, 4], [4, 6]]
//      aaa     aab      bc
//    then combine these intervals

//    After merged, we got [0,6], so we know "aaabbc" needs to be surrounded by tag.

public class AddBoldTagInString {
    static class Interval {
        int start, end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public String addBoldTag(String s, String[] dict) {
        List<Interval> list = new ArrayList<>();
        for (String str : dict) {
            int index = s.indexOf(str);
            while (index != -1) {
                list.add(new Interval(index, index + str.length()));
                index = s.indexOf(str, index + 1);
            }
        }

        list = merge(list);

        int prev = 0;
        StringBuilder sb = new StringBuilder();
        for (Interval interval : list) {
            sb.append(s, prev, interval.start).append("<b>").append(s, interval.start, interval.end).append("</b>");
            prev = interval.end;
        }
        if (prev < s.length()) {
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }


    private List<Interval> merge(List<Interval> list) {
        if (list.size() <= 1) {
            return list;
        }
        List<Interval> res = new ArrayList<>();
        Collections.sort(list, (a, b) -> Integer.compare(a.start, b.start));
        res.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            Interval temp = list.get(i);
            if (temp.start > res.get(res.size() - 1).end) {
                res.add(temp);
            } else {
                res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, temp.end);
            }
        }
        return res;
    }
}
