package string;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public String simplifyPath(String path) {
        /*
        The path starts with a single slash '/'.
        Any two directories are separated by a single slash '/'.
        The path does not end with a trailing '/'.
        The path only contains the directories on the path from the root directory
        to the target file or directory (i.e., no period '.' or double period '..')
        */

        // cd /a/b/c/.././././//d/  ->    /a/b/d

        // Initialize a stack
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");

        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : components) {

            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            }
            if (directory.equals("..")) {
                // If the current component is a "..", then
                // we pop an entry from the stack if it's non-empty
                if (!stack.isEmpty()) {
                    stack.pollFirst();
                }
            } else {
                // Finally, a legitimate directory name, so we add it
                // to our stack
                stack.offerFirst(directory);
            }
        }

        // Stick all the directory names together
        StringBuilder result = new StringBuilder();
        Deque<String> buffer = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            buffer.offerFirst(stack.pollFirst());
        }
        for (String dir : buffer) {
            result.append("/");
            result.append(dir);
        }

        return result.length() > 0 ? result.toString() : "/" ;
    }
}
