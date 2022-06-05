package data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://app.laicode.io/app/problem/288?plan=3">...</a>
 */

public class FirstNonRepeatingCharacterInStream {

    // Storage layer --> linked list
    // Index layer --> HashMap

    // maintain a set to avoid duplicate
    // maintain a map to reduce time of locating the node by establishing a relationship of <ch, node>

    // for any character, it could be in three states:
    // 1. not existed yet, it will not be in singled Map or
    // repeated Set
    // 2. only exists once, it will be in singled Map and
    // there will be a corresponding node in the list
    // 3. exists more than once, it will be in the repeated Set,
    // and it will be removed from the list

    private Map<Character, Node> map;
    private Set<Character> set;
    private static final Node HEAD = new Node('0'); // FNR from head
    private static final Node TAIL = new Node('0'); // add to tail

    static class Node {
        char ch;
        Node next;
        Node prev;

        Node(char ch) {
            this.ch = ch;
        }
    }

    public FirstNonRepeatingCharacterInStream() {
        // Initialize the class.
        HEAD.next = TAIL;
        TAIL.prev = HEAD;
        map = new HashMap<>();
        set = new HashSet<>();
    }

    public void read(char ch) {
        // Implement this method here.
        // if it is in the list, remove it, remove from map, and add to set
        // else if it is in the set, ignore
        // otherwise, add to list and map
        Node node = map.get(ch);
        if (node != null) {
            evict(node);
            return;
        }
        if (!set.contains(ch)) {
            node = new Node(ch);
            append(node);
        }
        // otherwise, if set contains ch, we do nothing
    }

    public Character firstNonRepeating() {
        return map.size() == 0 ? null : HEAD.next.ch;
    }

    private void evict(Node node) {
        map.remove(node.ch);
        set.add(node.ch);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = node.prev = null;
    }

    private void append(Node node) {
        map.put(node.ch, node);
        TAIL.prev.next = node;
        node.prev = TAIL.prev;
        TAIL.prev = node;
        node.next = TAIL;
    }
}
