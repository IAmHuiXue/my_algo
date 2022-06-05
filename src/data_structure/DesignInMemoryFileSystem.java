package data_structure;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/design-in-memory-file-system/">...</a>
 */

public class DesignInMemoryFileSystem {

    // in this design, an entry could be a file, or a directory.
    // a directory can contain files and or subdirectories
    // a file needs to have contents, while a directory does not need

    static class Entry {
        boolean isFile = false; // by default the entry is a directory
        HashMap<String, Entry> entries = new HashMap<>(); // <entryName, entryNode>
        String content = ""; // by default
    }

    Entry root;

    public DesignInMemoryFileSystem() {
        root = new Entry();
    }

    public List<String> ls(String path) {
        // traverse from the root until the last entry
        // if the last entry is a file, return the file name
        // otherwise, return the list of entries of the last entry/directory
        Entry e = root;
        List<String> res;
        if (!path.equals("/")) { // if the path is "/", do not go into the for loop
            String[] d = resolvePath(path);
            // ["", "a", "b"...] -> the first one is top level which is empty, so 'i' starts from 1
            for (int i = 1; i < d.length; i++) {
                e = e.entries.get(d[i]);
            }
            if (e.isFile) {
                res = Arrays.asList(d[d.length - 1]);
                return res;
            }
        }
        res = new ArrayList<>(e.entries.keySet());
        Collections.sort(res); // lexicographical order
        return res;
    }

    public void mkdir(String path) {
        // traverse from the root until the last entry
        // if any subdirectory does not exist, create one and continue
        Entry e = root;
        String[] d = resolvePath(path);
        for (int i = 1; i < d.length; i++) {
            e = e.entries.computeIfAbsent(d[i], k -> new Entry());
            // e = e.files.putIfAbsent(d[i], new File()); is wrong, because this API will return null if it puts the pair,
            // indicating the previous value is null !
        }
    }

    private String[] resolvePath(String path) {
        return path.split("/");
    }

    public void addContentToFile(String filePath, String content) {
        // traverse from the root until the last entry
        // if any subdirectory does not exist, create one and continue
        // at the last one, make it a file
        Entry e = root;
        String[] d = resolvePath(filePath);
        for (int i = 1; i < d.length; i++) {
            e = e.entries.computeIfAbsent(d[i], k -> new Entry());
        }
        e.isFile = true;
        e.content += content; // if it has been existed, do not override, append
    }

    public String readContentFromFile(String filePath) {
        // traverse from the root until the last entry
        // at the last one, return the content of the file
        Entry e = root;
        String[] d = resolvePath(filePath);
        for (int i = 1; i < d.length; i++) {
            e = e.entries.get(d[i]); // assume the file path is valid
        }
        return e.content;
    }
}
