package data_structure;

import java.util.*;

/** <a href="https://leetcode.com/problems/design-a-file-sharing-system/submissions/">...</a> */

public class DesignAFileSharingSystem {
}

class FileSharing {
    // One important assumption: once a user requests a chunk successfully, he owns the chunk.
    PriorityQueue<Integer> usableId = new PriorityQueue<>();
    int nextAvailableId = 1;
    Map<Integer, Set<Integer>> chunkMap = new HashMap<>();
    Map<Integer, List<Integer>> userMap = new HashMap<>();
    public FileSharing(int m) {
        for (int i = 1; i <= m; i++) {
            chunkMap.put(i, new HashSet<>());
        }
    }

    public int join(List<Integer> ownedChunks) {
        int userID = usableId.isEmpty() ? nextAvailableId++ : usableId.poll();
        for (int chunk : ownedChunks) {
            chunkMap.get(chunk).add(userID);
        }
        userMap.put(userID, ownedChunks);
        return userID;
    }

    public void leave(int userID) {
        List<Integer> ownedChunks = userMap.remove(userID);
        usableId.offer(userID);
        for (int chunk : ownedChunks) {
            chunkMap.get(chunk).remove(userID);
        }
    }

    public List<Integer> request(int userID, int chunkID) {
        Set<Integer> users = chunkMap.get(chunkID);
        List<Integer> res = new ArrayList<>(users);
        if (!users.isEmpty() && !users.contains(userID)) { // edge case: if userID already has chunkID, skip
            userMap.get(userID).add(chunkID);
            chunkMap.get(chunkID).add(userID);
        }
        Collections.sort(res);
        return res;
    }
}
