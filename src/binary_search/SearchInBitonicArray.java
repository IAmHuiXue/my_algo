package binary_search;

/** https://app.laicode.io/app/problem/401?plan=11 */

public class SearchInBitonicArray {
    public int search(int[] array, int target) {
        // assume array is not null and  no duplicates
        if (array.length == 0) {
            return -1;
        }
        int peek = findBitonic(array);
        int result = ClassicBS.ascendingBS(array, target, 0, peek);
        if (result == -1) {
            return ClassicBS.descendingBS(array, target, peek + 1, array.length - 1);
        }
        return result;
    }

    private int findBitonic(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // now l = r = peak index
        return left;
    }
}
