package binary_search;

/** https://app.laicode.io/app/problem/401?plan=11 */

public class SearchInBitonicArray {
    public int search(int[] array, int target) {
        // assume array is not null and  no duplicates
        if (array.length == 0) {
            return -1;
        }
        int peek = FindPeakElement.findPeakElement(array);
        int result = ClassicBS.ascendingBS(array, target, 0, peek);
        if (result == -1) {
            return ClassicBS.descendingBS(array, target, peek + 1, array.length - 1);
        }
        return result;
    }
}
