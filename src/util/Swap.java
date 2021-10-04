package util;

public class Swap {
    private Swap() {
    }

    public static <E> void swap(E[]array, int x, int y) {
        E tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
    public static void swap(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
    public static void swap(char[] array, int x, int y) {
        char tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

}
