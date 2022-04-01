package trick;

/*

You are given an array of non-negative integers numbers. You are allowed to choose any number from this array and swap
any two digits in it. If after the swap operation the number contains leading zeros, they can be omitted and not
considered (eg: 010 will be considered just 10).

Your task is to check whether it is possible to apply the swap operation at most once, so that the elements of the
resulting array are strictly increasing.

Example

For numbers = [1, 5, 10, 20], the output should be solution(numbers) = true.

The initial array is already strictly increasing, so no actions are required.

For numbers = [1, 3, 900, 10], the output should be solution(numbers) = true.

By choosing numbers[2] = 900 and swapping its first and third digits, the resulting number 009 is considered to be just 9.
So the updated array will look like [1, 3, 9, 10], which is strictly increasing.

For numbers = [13, 31, 30], the output should be solution(numbers) = false.

The initial array elements are not increasing.
By swapping the digits of numbers[0] = 13, the array becomes [31, 31, 30] which is not strictly increasing;
By swapping the digits of numbers[1] = 31, the array becomes [13, 13, 30] which is not strictly increasing;
By swapping the digits of numbers[2] = 30, the array becomes [13, 31, 3] which is not strictly increasing;
So, it's not possible to obtain a strictly increasing array, and the answer is false.

Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer numbers

An array of non-negative integers.

Guaranteed constraints:
1 ≤ numbers.length ≤ 103,
0 ≤ numbers[i] ≤ 103.

[output] boolean

Return true if it is possible to obtain a strictly increasing array by applying the digit-swap operation at most once,
and false otherwise.

 */

public class SwapOneNumToBecomeASC {
    public boolean solution(int[] numbers) {
        int index = -1;
        // do a linear scam to determine the num of irregular element
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] >= numbers[i]) {
                // if more than one is found
                if (index != -1) {
                    return false;
                }
                index = i;
            }
        }
        // if no irregular element is found, return true
        if (index == -1) {
            return true;
        }

        // two cases to try:
        // 1. change a[index], 2. change a[index - 1]
        return swapNum(numbers, index) || swapNum(numbers, index - 1);
    }

    boolean swapNum(int[] numbers, int index) {
        int prev = index - 1 < 0 ? Integer.MIN_VALUE : numbers[index - 1];
        int next = index + 1 == numbers.length ? Integer.MAX_VALUE : numbers[index + 1];
        return swapNumHelper(String.valueOf(numbers[index]).toCharArray(), prev, next);
    }

    boolean swapNumHelper(char[] c, int p, int n) {
        for (int i = 0; i < c.length - 1; i++) {
            for (int j = i + 1; j < c.length; j++) {
                swap(c, i, j);
                int value = Integer.parseInt(new String(c));
                if (value > p && value < n) {
                    return true;
                }
                swap(c, i, j);
            }
        }
        return false;
    }

    void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
