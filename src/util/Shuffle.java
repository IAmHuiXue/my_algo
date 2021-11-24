package util;

import java.util.*;

public class Shuffle {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        // if the input is an instance of class Collection, directly use the built-in API from Collections class
        Collections.shuffle(list);
        System.out.println(list);
        // or write the method yourself
        shuffle(list);
        System.out.println(list);

        // if the input is an array
        // write the method yourself
        shuffle(array);
        System.out.println(Arrays.toString(array));
    }

    /** modern version of Fisher-Yates shuffle */
    // O(n) runtime, O(1) space
    // starts from the last index
    // each time, randomly pick an index [0, lastIndex] including the last index
    // swap, and then decrement the last index
    public static void shuffle(int[] array) {
        for (int lastIndex = array.length - 1; lastIndex > 0; lastIndex--) { // range
            // randomly pick an index from [0, lastIndex]

//            Random rand = new Random();
//            int picked = rand.nextInt(lastIndex + 1);
            int picked = (int) (Math.random() * (lastIndex + 1)); // 把后面的部分 bracket 起来再 cast 成 int 很重要
            int tmp = array[picked];
            array[picked] = array[lastIndex];
            array[lastIndex] = tmp;
        }
    }

    public static void shuffle(List<Integer> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Collections.swap(list, i, j);
//            Integer num1 = list.get(i);
//            Integer num2 = list.get(j);
//            list.set(i, num2);
//            list.set(j, num1);
        }
    }
}
