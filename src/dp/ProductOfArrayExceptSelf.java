package dp;

/** https://leetcode.com/problems/product-of-array-except-self/ */

public class ProductOfArrayExceptSelf {
    static class OnTime {
        public int[] productExceptSelf(int[] nums) {
            // 2 <= nums.length <= 105

            // left[i] represents the prefix product from beginning to index i including i
            int[] left = new int[nums.length];
            // right[i] represents the suffix product from ending to index i including i
            int[] right = new int[nums.length];

            left[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                left[i] = left [i - 1] * nums[i];
            }
            right[nums.length - 1] = nums[nums.length - 1];
            for (int j = nums.length - 2; j >= 0; j--) {
                right[j] = right[j + 1] * nums[j];
            }

            int[] result = new int[nums.length];
            result[0] = right[1];
            result[nums.length - 1] = left[nums.length - 2];
            for (int i = 1; i < nums.length - 1; i++) {
                result[i] = left[i - 1] * right[i + 1];
            }
            return result;
        }
    }

    static class ConstantSpace {
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;
            int[] answer = new int[length];
            // answer[i] contains the product of all the elements to the left excluding index i
            // Note: for the element at index '0', there are no elements to the left,
            // so the answer[0] would be 1
            answer[0] = 1;
            for (int i = 1; i < length; i++) {
                // answer[i - 1] already contains the product of elements to the left of 'i - 1'
                // Simply multiplying it with nums[i - 1] would give the product of all
                // elements to the left of index 'i'
                answer[i] = nums[i - 1] * answer[i - 1];
            }
            // R contains the product of all the elements to the right
            // Note: for the element at index 'length - 1', there are no elements to the right,
            // so the R would be 1
            int R = 1;
            for (int i = length - 1; i >= 0; i--) {
                // For the index 'i', R would contain the
                // product of all elements to the right. We update R accordingly
                answer[i] = answer[i] * R;
                R *= nums[i];
            }
            return answer;
        }
    }
}
