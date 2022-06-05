package trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/word-search-ii/">...</a>
 */

// this demonstrates how to create + use TrieNode in practical problems

public class WordSearchII {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static class UseTrie {
        static class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isWord;
        }

        public static List<String> findWords(char[][] board, String[] words) {
            if (board == null || board.length == 0 || board[0].length == 0 ||
                    words == null || words.length == 0) {
                return new ArrayList<>();
                // throw new illegalArgumentException("invalid input");
            }

            // step 1: build the trie from the given list of words
            TrieNode root = buildDict(words);
            Set<String> result = new HashSet<>();
            int rows = board.length;
            int cols = board[0].length;
            boolean[][] visited = new boolean[rows][cols];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    dfs(root, board, i, j, sb, result, visited);
                }
            }
            return new ArrayList<>(result);
        }

        private static void dfs(TrieNode curNode, char[][] board, int x, int y, StringBuilder sb,
                                Set<String> result, boolean[][] visited) {
            if (curNode.isWord) {
                result.add(sb.toString()); // should not return yet
            }

            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
                return;
            }

            char curLetter = board[x][y];
            curNode = curNode.children[curLetter - 'a'];
            if (curNode == null) {
                return;
            }

            sb.append(curLetter);
            visited[x][y] = true;
            for (int[] dir : DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                dfs(curNode, board, nx, ny, sb, result, visited);
            }
            visited[x][y] = false;
            sb.deleteCharAt(sb.length() - 1);
        }

        private static TrieNode buildDict(String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode cur = root; // !
                for (char ch : word.toCharArray()) {
                    TrieNode next = cur.children[ch - 'a'];
                    if (next == null) {
                        next = new TrieNode();
                        cur.children[ch - 'a'] = next; // !
                    }
                    cur = next;
                }
                cur.isWord = true;
            }
            return root;
        }
        // m * n matrix, length of the word is l, size of the dictionary is k
        // time:
        //  build a trie:
        //   for each word -- O(k)
        //      insert -- O(l)
        //  search all words in dictionary:
        //    for i -- O(m)
        //       for j -- O(n)
        //          dfs() -- O(4^l) --> l level + 4 branches (4 directions)
        //              match path -- O(l)

        // O(k * l + m * n * (4 ^ l) * l)

        // space: O(m * n)
    }

    static class BruteForce {
        public static List<String> findWords(char[][] board, String[] words) {
            List<String> result = new ArrayList<>();
            if (board == null || board.length == 0 || board[0].length == 0 ||
                    words == null || words.length == 0) {
                return result;
            }
            Set<String> set = new HashSet<>(); // to avoid duplicate words in given String[] words
            for (String word : words) {
                if (set.add(word) && exist(board, word)) {
                    result.add(word);
                }
            }
            return result;
        }

        private static boolean exist(char[][] board, String word) {
            int rows = board.length;
            int cols = board[0].length;
            boolean[][] visited = new boolean[rows][cols]; // de-dup
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dfs(board, word, 0, visited, i, j)) {
                        return true;
                    }
                }
            }
            return false;
        }

        // visited represents visited cells on the path from (i, j) to (x, y) excluding (x, y)
        private static boolean dfs(char[][] board, String word, int index, boolean[][] visited, int x, int y) {
            // if all the characters are matched, we found a path representing the word
            if (index == word.length()) {
                return true;
            }
            // out of bound
            // visited before
            // curLetter does not match
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
                    || board[x][y] != word.charAt(index) || visited[x][y]) {
                return false;
            }
            visited[x][y] = true; // record the cell is used on the current DFS path
            for (int[] dir : DIRS) {
                int neiX = dir[0] + x;
                int neiY = dir[1] + y;
                if (dfs(board, word, index + 1, visited, neiX, neiY)) {
                    return true;
                }
            }
            visited[x][y] = false; // recover to make it available for other paths
            return false;
        }
        // m * n matrix, length of the word is l, size of the dictionary is k
        // time:
        //  for each word -- O(k)
        //  for i -- O(m)
        //      for j -- O(n)
        //          dfs() -- O(4^l) --> l level + 4 branches (4 directions)

        // O(k * m * n * (4 ^ l))
    }
}
