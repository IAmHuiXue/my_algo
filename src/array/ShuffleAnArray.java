package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <a href="https://leetcode.com/problems/shuffle-an-array/">...</a>
 */

public class ShuffleAnArray {
    static class CleanerSwap {
        private int[] nums;
        private static final Random RAND = new Random();

        public CleanerSwap(int[] nums) {
            this.nums = nums;
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return nums;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            if (nums == null) {
                return null;
            }
            int[] res = nums.clone();
            for (int j = 1; j < res.length; j++) {
                int i = RAND.nextInt(j + 1);
                swap(res, i, j);
            }
            return res;
        }

        private void swap(int[] a, int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    // by swapping elements around within the array itself,
    // we can avoid the linear space cost of the auxiliary array and the linear time cost of list modification
    static class FisherYates {
        private int[] array;
        private int[] original;

        private static final Random RAND = new Random();

        private int randRange(int min, int max) { // [min, max)
            return RAND.nextInt(max - min) + min;
        }

        private void swapAt(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public FisherYates(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return array;
        }

        public int[] shuffle() {
            for (int i = 0; i < array.length; i++) {
                // swap i with [i, array.length)
                swapAt(i, randRange(i, array.length));
            }
            return array;
        }
    }

    static class BruteForce {
        private int[] array;
        private int[] original;

        private static final Random RAND = new Random();

        private List<Integer> getArrayCopy() {
            List<Integer> asList = new ArrayList<>();
            for (int j : array) {
                asList.add(j);
            }
            return asList;
        }

        public BruteForce(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return array;
        }

        public int[] shuffle() {
            List<Integer> aux = getArrayCopy();

            for (int i = 0; i < array.length; i++) {
                int removeIdx = RAND.nextInt(aux.size());
                array[i] = aux.get(removeIdx);
                aux.remove(removeIdx); // O(n) time
            }

            return array;
        }
    }

}
