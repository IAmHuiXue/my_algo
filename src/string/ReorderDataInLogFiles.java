package string;

import java.util.Arrays;
import java.util.Comparator;

/** <a href="https://leetcode.com/problems/reorder-data-in-log-files/">https://leetcode.com/problems/reorder-data-in-log-files/</a> */

public class ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (l1, l2) -> {
            String[] split1 = l1.split(" ", 2);
            String[] split2 = l2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

            // case 1: if both logs are letters
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]); // use substring compares to substring directly
                // firstly compare the content
                if (cmp != 0) {
                    return cmp;
                }
                // logs of same content, then compare the id
                return split1[0].compareTo(split2[0]);
            }
            // case 2: one is letter and the other is digit
            if (isDigit1 && !isDigit2) {
                // letter-log comes before digit-log
                return 1;
            }
            if (!isDigit1) {
                return -1;
            }
            // case 3: both are digit
            // remain the order
            return 0;
        });

//        Comparator<String> myComp = new Comparator<String>() {
//            @Override
//            public int compare(String log1, String log2) {
//                // split each log into two parts: <identifier, content>
//                String[] split1 = log1.split(" ", 2);
//                String[] split2 = log2.split(" ", 2);
//
//                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
//                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
//
//                // case 1). both logs are letter-logs
//                if (!isDigit1 && !isDigit2) {
//                    // first compare the content
//                    int cmp = split1[1].compareTo(split2[1]);
//                    if (cmp != 0)
//                        return cmp;
//                    // logs of same content, compare the identifiers
//                    return split1[0].compareTo(split2[0]);
//                }
//
//                // case 2). one of logs is digit-log
//                if (!isDigit1 && isDigit2)
//                    // the letter-log comes before digit-logs
//                    return -1;
//                else if (isDigit1)
//                    return 1;
//                else
//                    // case 3). both logs are digit-log
//                    return 0;
//            }
//        };
//        Arrays.sort(logs, myComp);

        return logs;
    }
}
