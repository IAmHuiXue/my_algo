package square;

/*
Question:
You are given information on a Sock object such as color and foot (left or right). For example, consider the below as input:

black and left
pink and right
pink and left
black and right
black and right
You have to write a method which takes the following input and return the list of Sock object pairs (same color, different foot) which are:

(1, 4) *OR *(1, 5)
(2, 3)

Note if a sock is repeated in a pair then only return 1 pair where that sock is used.
 */

import java.util.*;

public class SockPairs {
    // given a list of sock objects, return a list of the index of the sock object pairs satisfying the requirement
    public static List<List<Integer>> sockPair(Sock[] socks) {
        Map<String, List<Integer>> visited = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < socks.length; i++) {
            Foot foot = socks[i].foot;
            Color color = socks[i].color;
            Foot oppo = foot == Foot.LEFT ? Foot.RIGHT : Foot.LEFT;
            String oppoType = color + oppo.toString();
            List<Integer> cur = visited.getOrDefault(oppoType, new LinkedList<>());
            if (!cur.isEmpty()) {
                res.add(Arrays.asList(cur.remove(0) + 1, i + 1));
                continue;
            }
            String type = color + foot.toString();
            visited.computeIfAbsent(type, k -> new LinkedList<>()).add(i);
        }
        return res;
    }

    public static List<List<Integer>> sockPair(List<String> socks) {
        // input is ["black and left", "pink and right"...]
        Map<String, List<Integer>> visited = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < socks.size(); i++) {
            String[] sock = socks.get(i).split("and");
            String color = sock[0].trim();
            String foot = sock[1].trim();
            String oppoKey = foot.equals("left") ? color + " and right" : color + " and left";
            List<Integer> list = visited.getOrDefault(oppoKey, new LinkedList<>());
            if (!list.isEmpty()) {
                res.add(Arrays.asList(list.remove(0) + 1, i + 1));
                continue;
            }
            String key = color + " and " + foot;
            visited.computeIfAbsent(key, k -> new LinkedList<>()).add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        /*
            black and left
            pink and right
            pink and left
            black and right
            black and right
         */

        Sock[] socks = new Sock[]{new Sock(Foot.LEFT, Color.BLACK),
                new Sock(Foot.RIGHT, Color.PINK), new Sock(Foot.LEFT, Color.PINK),
                new Sock(Foot.RIGHT, Color.BLACK), new Sock(Foot.RIGHT, Color.BLACK)};
        System.out.println(sockPair(socks));
        System.out.println(sockPair(Arrays.asList("black and left", "pink and right", "pink and left", "black and right", "black and right")));
    }
}

enum Foot {
    LEFT,
    RIGHT
}

enum Color {
    BLACK,
    PINK,
    BLUE,
    YELLOW,
    WHITE
}

class Sock {
    public Foot foot;
    public Color color;

    public Sock(Foot foot, Color color) {
        this.foot = foot;
        this.color = color;
    }
}
